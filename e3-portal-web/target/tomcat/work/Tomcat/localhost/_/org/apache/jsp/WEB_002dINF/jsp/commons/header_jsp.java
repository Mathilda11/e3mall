/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.47
 * Generated at: 2018-07-03 12:17:09 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.jsp.commons;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class header_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvarStatus_005fvar_005fitems;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvarStatus_005fvar_005fitems = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
    _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvarStatus_005fvar_005fitems.release();
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("\r\n");
      out.write("<!--shortcut start-->\r\n");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "shortcut.jsp", out, false);
      out.write("\r\n");
      out.write("<!--shortcut end-->\r\n");
      out.write("<div id=\"header\">\r\n");
      out.write("  <div class=\"header_inner\">\r\n");
      out.write("    <div class=\"logo\">\r\n");
      out.write("        \r\n");
      out.write("        <div class=\"index_topad\" id=\"playLogo\" style=\"\">\r\n");
      out.write("            <a href=\"/html/activity/1472179566.html\" target=\"_blank\">\r\n");
      out.write("              <img src=\"/images/html/20160829181637762.gif\">\r\n");
      out.write("            </a> \r\n");
      out.write("        </div>\r\n");
      out.write("\t\t<a name=\"sfbest_hp_hp_head_logo\" href=\"http://www.e3mall.cn\" class=\"trackref logoleft\">\r\n");
      out.write("\t\t</a>\r\n");
      out.write("\t\t<div class=\"logo-text\">\r\n");
      out.write("\t\t\t<img src=\"/images/html/logo_word.jpg\">\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("    <div class=\"index_promo\"></div>\r\n");
      out.write("    <div class=\"search\">\r\n");
      out.write("      <form action=\"http://localhost:8085/search.html\" id=\"searchForm\" name=\"query\" method=\"GET\">\r\n");
      out.write("        <input type=\"text\" class=\"text keyword ac_input\" name=\"keyword\" id=\"keyword\" value=\"\" style=\"color: rgb(153, 153, 153);\" onkeydown=\"javascript:if(event.keyCode==13) search_keys('searchForm');\" autocomplete=\"off\">\r\n");
      out.write("        <input type=\"button\" value=\"\" class=\"submit\" onclick=\"search_keys('searchForm')\">\r\n");
      out.write("      </form>\r\n");
      out.write("      <div class=\"search_hot\">\r\n");
      out.write("      ");
      if (_jspx_meth_c_005fforEach_005f0(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("<!--       <a target=\"_blank\" href=\"http://www.e3mall.cn/productlist/search?inputBox=1&amp;keyword=%E7%9F%B3%E6%A6%B4#trackref=sfbest_hp_hp_head_Keywords2\">石榴</a>\r\n");
      out.write("      <a target=\"_blank\" href=\"http://www.e3mall.cn/productlist/search?inputBox=1&amp;keyword=%E6%9D%BE%E8%8C%B8#trackref=sfbest_hp_hp_head_Keywords3\">松茸</a>\r\n");
      out.write("      <a target=\"_blank\" href=\"http://www.e3mall.cn/productlist/search?inputBox=1&amp;keyword=%E7%89%9B%E6%8E%92#trackref=sfbest_hp_hp_head_Keywords4\">牛排</a>\r\n");
      out.write("      <a target=\"_blank\" href=\"http://www.e3mall.cn/productlist/search?inputBox=1&amp;keyword=%E7%99%BD%E8%99%BE#trackref=sfbest_hp_hp_head_Keywords5\">白虾</a>\r\n");
      out.write("      <a target=\"_blank\" href=\"http://www.e3mall.cn/productlist/search?inputBox=1&amp;keyword=%E5%85%A8%E8%84%82%E7%89%9B%E5%A5%B6#trackref=sfbest_hp_hp_head_Keywords6\">全脂牛奶</a>\r\n");
      out.write("      <a target=\"_blank\" href=\"http://www.e3mall.cn/productlist/search?inputBox=1&amp;keyword=%E6%B4%8B%E6%B2%B3#trackref=sfbest_hp_hp_head_Keywords7\">洋河</a>\r\n");
      out.write("      <a target=\"_blank\" href=\"http://www.e3mall.cn/productlist/search?inputBox=1&amp;keyword=%E7%BB%BF%E8%B1%86#trackref=sfbest_hp_hp_head_Keywords8\">绿豆</a>\r\n");
      out.write("      <a target=\"_blank\" href=\"http://www.e3mall.cn/productlist/search?inputBox=1&amp;keyword=%E4%B8%80%E5%93%81%E7%8E%89#trackref=sfbest_hp_hp_head_Keywords9\">一品玉</a> -->\r\n");
      out.write("      </div>\r\n");
      out.write("    </div>\r\n");
      out.write("    <div class=\"shopingcar\" id=\"topCart\">\r\n");
      out.write("      <s class=\"setCart\"></s><a href=\"http://localhost:8090/cart/cart.html\" class=\"t\" rel=\"nofollow\">我的购物车</a><b id=\"cartNum\">0</b>\r\n");
      out.write("      <span class=\"outline\"></span>\r\n");
      out.write("      <span class=\"blank\"></span>\r\n");
      out.write("      <div id=\"cart_lists\">\r\n");
      out.write("        <!--cartContent-->   \r\n");
      out.write("        <div class=\"clear\"></div>\r\n");
      out.write("      </div>\r\n");
      out.write("    </div>\r\n");
      out.write("  </div>\r\n");
      out.write("</div>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }

  private boolean _jspx_meth_c_005fforEach_005f0(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  c:forEach
    org.apache.taglibs.standard.tag.rt.core.ForEachTag _jspx_th_c_005fforEach_005f0 = (org.apache.taglibs.standard.tag.rt.core.ForEachTag) _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvarStatus_005fvar_005fitems.get(org.apache.taglibs.standard.tag.rt.core.ForEachTag.class);
    _jspx_th_c_005fforEach_005f0.setPageContext(_jspx_page_context);
    _jspx_th_c_005fforEach_005f0.setParent(null);
    // /WEB-INF/jsp/commons/header.jsp(29,6) name = items type = javax.el.ValueExpression reqTime = true required = false fragment = false deferredValue = true expectedTypeName = java.lang.Object deferredMethod = false methodSignature = null
    _jspx_th_c_005fforEach_005f0.setItems(new org.apache.jasper.el.JspValueExpression("/WEB-INF/jsp/commons/header.jsp(29,6) '${adItemList }'",_el_expressionfactory.createValueExpression(_jspx_page_context.getELContext(),"${adItemList }",java.lang.Object.class)).getValue(_jspx_page_context.getELContext()));
    // /WEB-INF/jsp/commons/header.jsp(29,6) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fforEach_005f0.setVar("node");
    // /WEB-INF/jsp/commons/header.jsp(29,6) name = varStatus type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fforEach_005f0.setVarStatus("status");
    int[] _jspx_push_body_count_c_005fforEach_005f0 = new int[] { 0 };
    try {
      int _jspx_eval_c_005fforEach_005f0 = _jspx_th_c_005fforEach_005f0.doStartTag();
      if (_jspx_eval_c_005fforEach_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        do {
          out.write("\r\n");
          out.write("      <a target=\"_blank\" href=\"http://localhost:8085/search.html?keyword=");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${node.title }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
          out.write('"');
          out.write('>');
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${node.title }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
          out.write("</a>\r\n");
          out.write("      ");
          int evalDoAfterBody = _jspx_th_c_005fforEach_005f0.doAfterBody();
          if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
            break;
        } while (true);
      }
      if (_jspx_th_c_005fforEach_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        return true;
      }
    } catch (java.lang.Throwable _jspx_exception) {
      while (_jspx_push_body_count_c_005fforEach_005f0[0]-- > 0)
        out = _jspx_page_context.popBody();
      _jspx_th_c_005fforEach_005f0.doCatch(_jspx_exception);
    } finally {
      _jspx_th_c_005fforEach_005f0.doFinally();
      _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvarStatus_005fvar_005fitems.reuse(_jspx_th_c_005fforEach_005f0);
    }
    return false;
  }
}