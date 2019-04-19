package com.chehaha.api.wechat.xiao.action;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.JAXBException;

import com.chehaha.api.wechat.xiao.OfficialConst;
import com.chehaha.api.wechat.xiao.api.WechatConfig;
import com.chehaha.api.wechat.xiao.custserv.service.IConcatCustomerService;
import com.chehaha.api.wechat.xiao.custserv.service.ICustomerReplyService;
import com.chehaha.api.wechat.xiao.reply.entity.AutoReply;
import com.chehaha.api.wechat.xiao.reply.pojo.AutoReplyMode;
import com.chehaha.api.wechat.xiao.reply.pojo.WechatEventType;
import com.chehaha.api.wechat.xiao.reply.pojo.WechatUserMessage;
import com.chehaha.api.wechat.xiao.reply.repository.AutoReplyRepository;
import com.chehaha.api.wechat.xiao.reply.service.IReplyService;
import com.chehaha.api.wechat.xiao.util.SignUtil;
import com.chehaha.api.wechat.xiao.util.XmlUtil;
import com.chehaha.system.util.EventUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.chehaha.common.action.BaseController;
import com.chehaha.common.util.string.StringUtil;

@Controller
public class ReplyController extends BaseController {

	@Autowired
	private IReplyService ReplyService; // 回复操作

	@Autowired
	private IConcatCustomerService concatCustomerService; // 连接客服

	@Autowired
	private AutoReplyRepository autoReplyRepository;

	@Autowired
	private ICustomerReplyService customerReplyService;
	
	@RequestMapping(value = "/wechat/callback")
	public ResponseEntity<String> autoReply() throws IOException, JAXBException {
		HttpServletRequest request = getReqeust();
		//更换服务器
		String echostr = request.getParameter("echostr");
		if (StringUtil.isNotEmpty(echostr)) {
			String timestamp = request.getParameter("timestamp");
			String nonce = request.getParameter("nonce");
			String signature = request.getParameter("signature");
			if (SignUtil.checkSignature(signature, timestamp, nonce, WechatConfig.getCredential())) {
				return ResponseEntity.ok(echostr);
			}
		} else {
			// 接受消息
			WechatUserMessage userMsg = XmlUtil.toObject(getInputStream(), WechatUserMessage.class);
			//回复消息
			String outMsg = "";
			//用户
			String openId = userMsg.getFromUserName();
			if (openId != null) {
				String msgType = userMsg.getMsgType();
				//接受消息类型
				switch (msgType) {
				case "event":
					String event = userMsg.getEvent().toString();
					//事件码不为空
					if(userMsg.getEventKey()!=null&&!userMsg.getEventKey().isEmpty()) { 
						outMsg = AutoReplyEventMsg(userMsg.getEventKey(),openId);
					}
					if(!event.isEmpty()){
						//关注事件
						if(event.equals(WechatEventType.subscribe.toString())) {
							customerReplyService.customerServiceReply(openId, OfficialConst.welcome);
							EventUtil.wechatSubscribeEvent(openId,true);
						}
						//取消关注事件
						if(event.equals(WechatEventType.unsubscribe.toString())) {
							EventUtil.wechatSubscribeEvent(openId,false);
						}
					}

					break;
				case "text":
					outMsg = AutoReplyTextMsg(userMsg.getContent(),openId);
					break;
				default:
					break;
				}
				// 必须设置，否则会中文乱码
				HttpHeaders headers = new HttpHeaders();
				MediaType mediaType = new MediaType("text", "html", Charset.forName("utf-8"));
				headers.setContentType(mediaType);
				return new ResponseEntity<String>(outMsg, headers, HttpStatus.OK);
			}
		}
		return  ResponseEntity.ok(null);
	}

	//事件消息处理 	
	private String AutoReplyEventMsg(String eventKey,String openId) {
		//自定义菜单事件
		AutoReplyMode arm = AutoReplyMode.TEXT;
		//特殊处理,联系客服,优先于数据库指令匹配
		String msg =  concatCsvWithKey(eventKey,openId);
		if(msg!=null) {
			return msg;
		}
		if(eventKey.equals(OfficialConst.BUSINESS_CODE)) {
			return  ReplyService.autoReplyMessage(arm, openId, OfficialConst.ZX_INFOMATION);
		}
		if(eventKey.equals(OfficialConst.TS)) {
			return  ReplyService.autoReplyMessage(arm, openId, OfficialConst.BUSINESS_TX_REPLY);
		}
		if(eventKey.equals(OfficialConst.DONE_BUSINESS)) {
			return  ReplyService.autoReplyMessage(arm, openId, OfficialConst.BUSINESS_DONE_REPLY);
		}
		return null;
	}
	
	
	
	//非事件类型消息 这里文本消息处理
	private String AutoReplyTextMsg(String content,String openId) {
		AutoReplyMode arm = AutoReplyMode.TEXT;
		//特殊处理,联系客服,优先于数据库指令匹配,输入的消息0也可以用于联系客服
		String msg =  concatCsvWithKey(content,openId);
		if(msg!=null) {
			return msg;
		}
		//指令匹配得到数据库内容
		String matching = MatchingMysql(content);
		if(matching !=null) {
			return ReplyService.autoReplyMessage(arm, openId, matching);
		}
		return null;
	}
	
	//连接客服指令
	private String concatCsvWithKey(String content,String openId) {
		AutoReplyMode arm = AutoReplyMode.TEXT;
		if(content.equals(OfficialConst.CSV_EVENT_CODE)) {
			String msg = concatCustomerService.copyToMoreCustomerService(openId);
			if(msg == null) {
				//客服不在线
				return ReplyService.autoReplyMessage(arm, openId, OfficialConst.csvNotUnline);
			}else {
				//客服主动回复一段文字
				customerReplyService.customerServiceReply(openId, OfficialConst.concatCsv);
				//执行连接客服
				return msg;
			}
		}
		return null;
	}
	
	//匹配数据库指令进行回复
	private String MatchingMysql(String content) {
		AutoReplyMode arm = AutoReplyMode.TEXT;
		//判断是否找到相应指令
		boolean find = false;
		//匹配数据库中所有指令  可以用缓存来做
		List<AutoReply> list = autoReplyRepository.findAll();
		for (AutoReply ap : list) {
			// 事件码和key相同
			if (content != null && ap.getKeyWord().equals(content)) {
				// 对内容进行解析
				content = analysisString(ap.getText());
				find = true;
				return content;
			}
		}
		//没有找到相应指令
		if(!find) {
			AutoReply ar = autoReplyRepository.findByKeyWord(OfficialConst.unKnownCommand);
			if(ar == null){
				return "";
			}
			content =  analysisString(ar.getText());
			return content;
		}
		return null;
	}
	
	
	// 读取流中的内容转换成一个字符串。
	private String getInputStream() throws IOException {
		InputStream is = getReqeust().getInputStream();
		ByteArrayOutputStream result = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int length;
		while ((length = is.read(buffer)) != -1) {
			result.write(buffer, 0, length);
		}
		return result.toString("UTF-8");
	}

	// 对它进行解析
	private String analysisString(String content) {
		String newContent = "";
		// 如果包含,
		if (content.contains(",")) {
			String[] str = content.split(",");
			for (int i = 0; i < str.length; i++) {
				StringBuffer sb = new StringBuffer(str[i]);
				// 是否包含"@",@和m是对应的，@开头,m结尾
				if (str[i].contains("@")) {
					newContent += sb.substring(sb.indexOf("@") + 1, sb.indexOf("m"));
				} else {
					newContent += sb.toString();
				}
				// 加句子的结束标识符。
				if (i == str.length - 1) {
					newContent += "";
				} else {
					newContent += ",";
				}
			}
		} else {
			// 不包含,
			newContent += content;
		}
		return newContent;
	}

}
