/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.47
 * Generated at: 2019-01-08 03:00:13 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class end4_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
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
      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\r\n");
      out.write("<title>Insert title here</title>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("\t<select name=\"year\" id=\"year\">\r\n");
      out.write("\t\t<option value=\"2011\">2011</option>\r\n");
      out.write("\t\t<option value=\"2012\">2012</option>\r\n");
      out.write("\t\t<option value=\"2012\">2013</option>\r\n");
      out.write("\t</select>\r\n");
      out.write("\t<select name=\"month\" id=\"month\">\r\n");
      out.write("\t\r\n");
      out.write("\t</select>\r\n");
      out.write("\t<select name=\"day\" id=\"day\">\r\n");
      out.write("\t\r\n");
      out.write("\t</select>\r\n");
      out.write("\t\r\n");
      out.write("\t<script type=\"text/javascript\">\r\n");
      out.write("\t\tvar year = document.querySelector(\"#year\");\r\n");
      out.write("\t\tvar month = document.querySelector(\"#month\");\r\n");
      out.write("\t\tvar day = document.querySelector(\"#day\");\r\n");
      out.write("\t\t//年份改变\r\n");
      out.write("\t\tyear.onchange = function(){\r\n");
      out.write("\t\t\tmonth.innerHTML = null;\r\n");
      out.write("\t\t\tfor(var i = 1;i<13;i++){\r\n");
      out.write("\t\t\t\tvar oneMonth = document.createElement(\"option\");\r\n");
      out.write("\t\t\t\toneMonth.value = i;\r\n");
      out.write("\t\t\t\toneMonth.innerHTML = i;\r\n");
      out.write("\t\t\t\tmonth.appendChild(oneMonth);\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t//创建天\r\n");
      out.write("\t\tfunction crateDay(number){\r\n");
      out.write("\t\t\tfor(var i = 1;i<number;i++){\r\n");
      out.write("\t\t\t\tvar oneDay = document.createElement(\"option\"); \r\n");
      out.write("\t\t\t\toneDay.value = i;\r\n");
      out.write("\t\t\t\toneDay.innerHTML = i;\r\n");
      out.write("\t\t\t\tday.appendChild(oneDay);\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\t//月份改变\r\n");
      out.write("\t\tmonth.onchange = function(){\r\n");
      out.write("\t\t\tday.innerHTML = null;\r\n");
      out.write("\t\t\tif(year.value%4 == 0&&month.value == 2){\r\n");
      out.write("\t\t\t\tcrateDay(30);\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\t\tvar arr = [4,6,9,11];\r\n");
      out.write("\t\t\t\tvar bool = false;\r\n");
      out.write("\t\t\t\tfor(var i=0;i<arr.length;i++){\r\n");
      out.write("\t\t\t\t\tif(month.value == arr[i]){\r\n");
      out.write("\t\t\t\t\t\tbool = true;\r\n");
      out.write("\t\t\t\t\t\tbreak;\r\n");
      out.write("\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t\tif(month.value == 2&&year.value%4 != 0){\r\n");
      out.write("\t\t\t\t\tcrateDay(29);\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t\tif(bool == true&&month.value != 2){\r\n");
      out.write("\t\t\t\t\tcrateDay(31);\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t\tif(bool == false&&month.value != 2){\r\n");
      out.write("\t\t\t\t\tcrateDay(32);\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t</script>\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
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
}