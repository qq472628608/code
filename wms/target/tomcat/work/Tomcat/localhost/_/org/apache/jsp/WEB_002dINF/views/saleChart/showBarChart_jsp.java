/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.47
 * Generated at: 2018-12-16 14:23:50 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.views.saleChart;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class showBarChart_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("<!DOCTYPE HTML>\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">\r\n");
      out.write("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\r\n");
      out.write("<title>图表分析</title>\r\n");
      out.write("\r\n");
      out.write("<style type=\"text/css\">\r\n");
      out.write("</style>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("\t<script type=\"text/javascript\" src=\"/js/jquery/jquery.js\"></script>\r\n");
      out.write("\t<script src=\"/js/jquery/plugins/highCharts/highcharts.js\"></script>\r\n");
      out.write("\t<script src=\"/js/jquery/plugins/highCharts/exporting.js\"></script>\r\n");
      out.write("\t<script src=\"/js/jquery/plugins/highCharts/export-data.js\"></script>\r\n");
      out.write("\r\n");
      out.write("\t<div id=\"container\"\r\n");
      out.write("\t\tstyle=\"min-width: 310px; height: 400px; margin: 0 auto\"></div>\r\n");
      out.write("\r\n");
      out.write("\t<script type=\"text/javascript\">\r\n");
      out.write("\t\tvar opener = window.opener.document.querySelector(\"#searchForm\");\r\n");
      out.write("\t\t$.get(\"/saleChart_getJson?\" + $(opener).serialize(),function(data) {\r\n");
      out.write("\t\tvar names = data.groupNames;\r\n");
      out.write("\t\tvar amounts = data.amounts;\r\n");
      out.write("\r\n");
      out.write("\t\tHighcharts.chart(\r\n");
      out.write("\t\t\t\t\t\t'container',\r\n");
      out.write("\t\t\t\t\t\t{\r\n");
      out.write("\t\t\t\t\t\t\tchart : {\r\n");
      out.write("\t\t\t\t\t\t\ttype : 'column'\r\n");
      out.write("\t\t\t\t\t\t},\r\n");
      out.write("\t\t\t\t\t\ttitle : {\r\n");
      out.write("\t\t\t\t\t\t\ttext : '采购报表图表分析'\r\n");
      out.write("\t\t\t\t\t\t},\r\n");
      out.write("\t\t\t\t\t\tsubtitle : {\r\n");
      out.write("\t\t\t\t\t\t\ttext : 'Source: recentlydata'\r\n");
      out.write("\t\t\t\t\t\t},\r\n");
      out.write("\t\t\t\t\t\txAxis : {\r\n");
      out.write("\t\t\t\t\t\t\tcategories : names,\r\n");
      out.write("\t\t\t\t\t\t\tcrosshair : true\r\n");
      out.write("\t\t\t\t\t\t},\r\n");
      out.write("\t\t\t\t\t\tyAxis : {\r\n");
      out.write("\t\t\t\t\t\t\tmin : 0,\r\n");
      out.write("\t\t\t\t\t\t\ttitle : {\r\n");
      out.write("\t\t\t\t\t\t\ttext : '交易金额(元)'\r\n");
      out.write("\t\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t\t},\r\n");
      out.write("\t\t\t\t\t\ttooltip : {\r\n");
      out.write("\t\t\t\t\t\t\theaderFormat : '<span style=\"font-size:10px\">{point.key}</span><table>',\r\n");
      out.write("\t\t\t\t\t\t\tpointFormat : '<tr><td style=\"color:{series.color};padding:0\">{series.name}: </td>'\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t+ '<td style=\"padding:0\"><b>{point.y:.1f} mm</b></td></tr>',\r\n");
      out.write("\t\t\t\t\t\t\tfooterFormat : '</table>',\r\n");
      out.write("\t\t\t\t\t\t\tshared : true,\r\n");
      out.write("\t\t\t\t\t\t\tuseHTML : true\r\n");
      out.write("\t\t\t\t\t\t},\r\n");
      out.write("\t\t\t\t\t\tplotOptions : {\r\n");
      out.write("\t\t\t\t\t\t\tcolumn : {\r\n");
      out.write("\t\t\t\t\t\t\tpointPadding : 0.2,\r\n");
      out.write("\t\t\t\t\t\t\tborderWidth : 0\r\n");
      out.write("\t\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t\t},\r\n");
      out.write("\t\t\t\t\t\tseries : [ {\r\n");
      out.write("\t\t\t\t\t\t\tname : '金额',\r\n");
      out.write("\t\t\t\t\t\t\tdata : amounts\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t} ]\r\n");
      out.write("\t\t\t\t\t\t});\r\n");
      out.write("\t\t\t\t});\r\n");
      out.write("\t</script>\r\n");
      out.write("</body>\r\n");
      out.write("</html>\r\n");
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
