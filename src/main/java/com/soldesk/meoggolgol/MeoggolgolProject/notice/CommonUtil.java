package com.soldesk.meoggolgol.MeoggolgolProject.notice;

import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;
import org.springframework.stereotype.Component;

@Component
public class CommonUtil {
	
	public static String markdown(NoticeResponse notice) {
        Parser parser = Parser.builder().build();
        Node document = parser.parse(notice.getContent());
        HtmlRenderer renderer = HtmlRenderer.builder().build();
        return renderer.render(document);
    }
}

