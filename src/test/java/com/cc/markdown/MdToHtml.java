package com.cc.markdown;

import org.commonmark.Extension;
import org.commonmark.ext.gfm.tables.TablesExtension;
import org.commonmark.node.Link;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.AttributeProvider;
import org.commonmark.renderer.html.HtmlRenderer;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author : cc
 * @date : 2019-03-10  9:37
 */
public class MdToHtml {

    @Test
    public void MdTest(){
        List<Extension> extensions = Arrays.asList(TablesExtension.create());
        Parser parser     = Parser.builder().extensions(extensions).build();
        Node document   = parser.parse("fdsfd\n" +
                "```js\n" +
                "public static void main(){\n" +
                "  System.out.println(\"dd\");\n" +
                "}\n" +
                "``` \n" +
                " ![alt](http://localhost:8081/admin/images/unicorn.png)\n");
        HtmlRenderer renderer = HtmlRenderer.builder()
                .attributeProviderFactory(context -> new LinkAttributeProvider())
                .extensions(extensions).build();

        String content = renderer.render(document);
    }
    static class LinkAttributeProvider implements AttributeProvider {
        @Override
        public void setAttributes(Node node, String tagName, Map<String, String> attributes) {
            if (node instanceof Link) {
                attributes.put("target", "_blank");
            }
        }
    }

}
