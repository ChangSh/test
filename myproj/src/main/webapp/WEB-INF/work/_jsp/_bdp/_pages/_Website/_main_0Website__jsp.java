/*
 * JSP generated by Resin-4.0.35 (built Tue, 12 Feb 2013 10:05:50 PST)
 */

package _jsp._bdp._pages._Website;
import javax.servlet.*;
import javax.servlet.jsp.*;
import javax.servlet.http.*;

public class _main_0Website__jsp extends com.caucho.jsp.JavaPage
{
  private static final java.util.HashMap<String,java.lang.reflect.Method> _jsp_functionMap = new java.util.HashMap<String,java.lang.reflect.Method>();
  private boolean _caucho_isDead;
  private boolean _caucho_isNotModified;
  private com.caucho.jsp.PageManager _jsp_pageManager;
  
  public void
  _jspService(javax.servlet.http.HttpServletRequest request,
              javax.servlet.http.HttpServletResponse response)
    throws java.io.IOException, javax.servlet.ServletException
  {
    javax.servlet.http.HttpSession session = request.getSession(true);
    com.caucho.server.webapp.WebApp _jsp_application = _caucho_getApplication();
    com.caucho.jsp.PageContextImpl pageContext = _jsp_pageManager.allocatePageContext(this, _jsp_application, request, response, null, session, 8192, true, false);

    TagState _jsp_state = null;

    try {
      _jspService(request, response, pageContext, _jsp_application, session, _jsp_state);
    } catch (java.lang.Throwable _jsp_e) {
      pageContext.handlePageException(_jsp_e);
    } finally {
      _jsp_pageManager.freePageContext(pageContext);
    }
  }
  
  private void
  _jspService(javax.servlet.http.HttpServletRequest request,
              javax.servlet.http.HttpServletResponse response,
              com.caucho.jsp.PageContextImpl pageContext,
              javax.servlet.ServletContext application,
              javax.servlet.http.HttpSession session,
              TagState _jsp_state)
    throws Throwable
  {
    javax.servlet.jsp.JspWriter out = pageContext.getOut();
    final javax.el.ELContext _jsp_env = pageContext.getELContext();
    javax.servlet.ServletConfig config = getServletConfig();
    javax.servlet.Servlet page = this;
    javax.servlet.jsp.tagext.JspTag _jsp_parent_tag = null;
    com.caucho.jsp.PageContextImpl _jsp_parentContext = pageContext;
    response.setContentType("text/html; charset=UTF-8");

    out.write(_jsp_string0, 0, _jsp_string0.length);
    
  String contextPath = (String) request.getContextPath();
			request.setAttribute("isMenu", "true");

    out.write(_jsp_string1, 0, _jsp_string1.length);
    out.print((contextPath ));
    out.write(_jsp_string2, 0, _jsp_string2.length);
    out.print((contextPath));
    out.write(_jsp_string3, 0, _jsp_string3.length);
    out.print((contextPath));
    out.write(_jsp_string4, 0, _jsp_string4.length);
    out.print((contextPath));
    out.write(_jsp_string5, 0, _jsp_string5.length);
    out.print((contextPath));
    out.write(_jsp_string6, 0, _jsp_string6.length);
    out.print((contextPath));
    out.write(_jsp_string7, 0, _jsp_string7.length);
    out.print((contextPath));
    out.write(_jsp_string8, 0, _jsp_string8.length);
    out.print((contextPath));
    out.write(_jsp_string9, 0, _jsp_string9.length);
    out.print((contextPath));
    out.write(_jsp_string10, 0, _jsp_string10.length);
    out.print((contextPath));
    out.write(_jsp_string11, 0, _jsp_string11.length);
    out.print((contextPath));
    out.write(_jsp_string12, 0, _jsp_string12.length);
    out.print((contextPath));
    out.write(_jsp_string13, 0, _jsp_string13.length);
    out.print((contextPath));
    out.write(_jsp_string14, 0, _jsp_string14.length);
    out.print((contextPath));
    out.write(_jsp_string15, 0, _jsp_string15.length);
    out.print((contextPath));
    out.write(_jsp_string16, 0, _jsp_string16.length);
    out.print((contextPath));
    out.write(_jsp_string17, 0, _jsp_string17.length);
    out.print((contextPath));
    out.write(_jsp_string18, 0, _jsp_string18.length);
    out.print((contextPath));
    out.write(_jsp_string19, 0, _jsp_string19.length);
    out.print((contextPath));
    out.write(_jsp_string20, 0, _jsp_string20.length);
    out.print((contextPath));
    out.write(_jsp_string21, 0, _jsp_string21.length);
    out.print((contextPath));
    out.write(_jsp_string22, 0, _jsp_string22.length);
    out.print((contextPath));
    out.write(_jsp_string23, 0, _jsp_string23.length);
    out.print((contextPath));
    out.write(_jsp_string24, 0, _jsp_string24.length);
    out.print((contextPath));
    out.write(_jsp_string25, 0, _jsp_string25.length);
    out.print((contextPath));
    out.write(_jsp_string26, 0, _jsp_string26.length);
    out.print((contextPath));
    out.write(_jsp_string27, 0, _jsp_string27.length);
    out.print((contextPath));
    out.write(_jsp_string28, 0, _jsp_string28.length);
    out.print((contextPath));
    out.write(_jsp_string29, 0, _jsp_string29.length);
    out.print((contextPath));
    out.write(_jsp_string30, 0, _jsp_string30.length);
    out.print((contextPath));
    out.write(_jsp_string31, 0, _jsp_string31.length);
    out.print((contextPath));
    out.write(_jsp_string32, 0, _jsp_string32.length);
    out.print((contextPath));
    out.write(_jsp_string33, 0, _jsp_string33.length);
    out.write(_jsp_string34, 0, _jsp_string34.length);
    out.write(_jsp_string35, 0, _jsp_string35.length);
  }

  private com.caucho.make.DependencyContainer _caucho_depends
    = new com.caucho.make.DependencyContainer();

  public java.util.ArrayList<com.caucho.vfs.Dependency> _caucho_getDependList()
  {
    return _caucho_depends.getDependencies();
  }

  public void _caucho_addDepend(com.caucho.vfs.PersistentDependency depend)
  {
    super._caucho_addDepend(depend);
    _caucho_depends.add(depend);
  }

  protected void _caucho_setNeverModified(boolean isNotModified)
  {
    _caucho_isNotModified = true;
  }

  public boolean _caucho_isModified()
  {
    if (_caucho_isDead)
      return true;

    if (_caucho_isNotModified)
      return false;

    if (com.caucho.server.util.CauchoSystem.getVersionId() != -8128084403448394333L)
      return true;

    return _caucho_depends.isModified();
  }

  public long _caucho_lastModified()
  {
    return 0;
  }

  public void destroy()
  {
      _caucho_isDead = true;
      super.destroy();
    TagState tagState;
  }

  public void init(com.caucho.vfs.Path appDir)
    throws javax.servlet.ServletException
  {
    com.caucho.vfs.Path resinHome = com.caucho.server.util.CauchoSystem.getResinHome();
    com.caucho.vfs.MergePath mergePath = new com.caucho.vfs.MergePath();
    mergePath.addMergePath(appDir);
    mergePath.addMergePath(resinHome);
    com.caucho.loader.DynamicClassLoader loader;
    loader = (com.caucho.loader.DynamicClassLoader) getClass().getClassLoader();
    String resourcePath = loader.getResourcePathSpecificFirst();
    mergePath.addClassPath(resourcePath);
    com.caucho.vfs.Depend depend;
    depend = new com.caucho.vfs.Depend(appDir.lookup("bdp/pages/Website/main_Website.jsp"), -8220618737200677181L, false);
    _caucho_depends.add(depend);
    depend = new com.caucho.vfs.Depend(appDir.lookup("bdp/commons/statics.jsp"), 7130047953460554352L, false);
    _caucho_depends.add(depend);
  }

  final static class TagState {

    void release()
    {
    }
  }

  public java.util.HashMap<String,java.lang.reflect.Method> _caucho_getFunctionMap()
  {
    return _jsp_functionMap;
  }

  public void caucho_init(ServletConfig config)
  {
    try {
      com.caucho.server.webapp.WebApp webApp
        = (com.caucho.server.webapp.WebApp) config.getServletContext();
      init(config);
      if (com.caucho.jsp.JspManager.getCheckInterval() >= 0)
        _caucho_depends.setCheckInterval(com.caucho.jsp.JspManager.getCheckInterval());
      _jsp_pageManager = webApp.getJspApplicationContext().getPageManager();
      com.caucho.jsp.TaglibManager manager = webApp.getJspApplicationContext().getTaglibManager();
      manager.addTaglibFunctions(_jsp_functionMap, "shiro", "http://shiro.apache.org/tags");
      com.caucho.jsp.PageContextImpl pageContext = new com.caucho.jsp.InitPageContextImpl(webApp, this);
    } catch (Exception e) {
      throw com.caucho.config.ConfigException.create(e);
    }
  }

  private final static char []_jsp_string18;
  private final static char []_jsp_string7;
  private final static char []_jsp_string26;
  private final static char []_jsp_string13;
  private final static char []_jsp_string19;
  private final static char []_jsp_string6;
  private final static char []_jsp_string32;
  private final static char []_jsp_string5;
  private final static char []_jsp_string0;
  private final static char []_jsp_string30;
  private final static char []_jsp_string21;
  private final static char []_jsp_string4;
  private final static char []_jsp_string28;
  private final static char []_jsp_string15;
  private final static char []_jsp_string35;
  private final static char []_jsp_string27;
  private final static char []_jsp_string14;
  private final static char []_jsp_string29;
  private final static char []_jsp_string24;
  private final static char []_jsp_string34;
  private final static char []_jsp_string11;
  private final static char []_jsp_string23;
  private final static char []_jsp_string17;
  private final static char []_jsp_string2;
  private final static char []_jsp_string9;
  private final static char []_jsp_string33;
  private final static char []_jsp_string20;
  private final static char []_jsp_string3;
  private final static char []_jsp_string22;
  private final static char []_jsp_string12;
  private final static char []_jsp_string25;
  private final static char []_jsp_string1;
  private final static char []_jsp_string10;
  private final static char []_jsp_string16;
  private final static char []_jsp_string31;
  private final static char []_jsp_string8;
  static {
    _jsp_string18 = "/resources/bdp/js/jquery/jquery-validate/jquery.validate.min.js\"></script>\r\n<script src=\"".toCharArray();
    _jsp_string7 = "/resources/bdp/bootstrap/datetimepicker/css/bootstrap-datetimepicker.min.css\" rel=\"stylesheet\" media=\"screen\">\r\n<!--  \u5f15\u5165bootstrap-3.0.3.min.css,\u89e3\u51b3\u4e86\u4e0b\u62c9\u9009\u62e9\u6837\u5f0f\u95ee\u9898\u3001\u8868\u683c\u5b57\u4f53\u5168\u90e8\u52a0\u7c97\u95ee\u9898-->\r\n<link href=\"".toCharArray();
    _jsp_string26 = "/resources/bdp/bootstrap/js/summernote.js\"></script>\r\n\r\n<script src=\"".toCharArray();
    _jsp_string13 = "/resources/bdp/highchart/exporting.js\"></script>\r\n<script src=\"".toCharArray();
    _jsp_string19 = "/resources/bdp/js/jquery/jquery-validate/messages_zh.js\"></script>\r\n<script src=\"".toCharArray();
    _jsp_string6 = "/resources/bdp/bootstrap/css/bootstrap-multiselect.css\" rel=\"stylesheet\">\r\n<link href=\"".toCharArray();
    _jsp_string32 = "/resources/bdp/js/bdp/xmlTohtml.js\"></script>\r\n\r\n<style type=\"text/css\">\r\n.bootstrap-table td {\r\n	text-overflow: ellipsis;\r\n	white-space: nowrap;\r\n	overflow: hidden;\r\n	max-width: 200px;\r\n}\r\n\r\n#Website_view_form .col-sm-9 label {\r\n	font-weight: normal;\r\n}\r\n\r\n#Website_form .col-sm-9 label {\r\n	font-weight: normal;\r\n}\r\n</style>\r\n<script type=\"text/javascript\">\r\nvar contextPath=\"".toCharArray();
    _jsp_string5 = "/resources/bdp/bootstrap/css/metisMenu.min.css\" rel=\"stylesheet\">\r\n<link href=\"".toCharArray();
    _jsp_string0 = "\r\n\r\n<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\r\n<html>\r\n<head>\r\n\r\n\r\n\r\n".toCharArray();
    _jsp_string30 = "/resources/bdp/bootstrap/export/FileSaver.min.js\"></script>\r\n<script src=\"".toCharArray();
    _jsp_string21 = "/resources/bdp/js/jquery/jquery.ztree.excheck-3.5.min.js\"></script>\r\n<script src=\"".toCharArray();
    _jsp_string4 = "/resources/bdp/css/orgStructTree/bootstrap/bootstrap-table.min.css\" rel=\"stylesheet\"  />\r\n<link href=\"".toCharArray();
    _jsp_string28 = "/resources/bdp/js/bdp/commons-operate.js\"></script>\r\n\r\n\r\n<!-- \u5f15\u5165\u5bfc\u51fa\u8868\u683c\u5230\u6587\u4ef6\u7684\u63d2\u4ef6 -->\r\n<script src=\"".toCharArray();
    _jsp_string15 = "/resources/bdp/js/bdp/orgStructTree/bootstrap/bootstrap-table-all.min.js\"></script>\r\n<script src=\"".toCharArray();
    _jsp_string35 = "label> \r\n               <div class='col-sm-9'> \r\n                 <label name='id' style='padding-top: 6px;'></label> \r\n               </div> \r\n             </div> \r\n             <div class='form-group'> \r\n             <label class='col-sm-3 control-label'>\u7f51\u7ad9\u540d\u79f0\uff1a</label> \r\n               <div class='col-sm-9'> \r\n                 <label name='websitename' style='padding-top: 6px;'></label> \r\n               </div> \r\n             </div> \r\n\r\n             <div class='form-group'> \r\n             <label class='col-sm-3 control-label'>\u7f51\u7ad9\u62fc\u97f3\uff1a</label> \r\n               <div class='col-sm-9'> \r\n                 <label name='websitepy' style='padding-top: 6px;'></label> \r\n               </div> \r\n             </div> \r\n             <div class='form-group'> \r\n             <label class='col-sm-3 control-label'>\u6dfb\u52a0\u65f6\u95f4\uff1a</label> \r\n               <div class='col-sm-9'> \r\n                 <label name='addtime' style='padding-top: 6px;'></label> \r\n               </div> \r\n             </div> \r\n             <div class='form-group'> \r\n             <label class='col-sm-3 control-label'>\u6dfb\u52a0\u4eba\uff1a</label> \r\n               <div class='col-sm-9'> \r\n                 <label name='addperson' style='padding-top: 6px;'></label> \r\n               </div> \r\n             </div> \r\n             <div class='form-group'> \r\n             <label class='col-sm-3 control-label'>\u5907\u6ce8\uff1a</label> \r\n               <div class='col-sm-9'> \r\n                 <label name='memo' style='padding-top: 6px;'></label> \r\n               </div> \r\n             </div> \r\n												\r\n						</form>\r\n					</div>\r\n					<div class=\"modal-footer\">\r\n						<button type=\"button\" class=\"btn btn-default\" data-dismiss=\"modal\">\u5173\u95ed</button>\r\n					</div>\r\n				</div>\r\n			</div>\r\n		</div>\r\n	</div>\r\n</body>\r\n</html>\r\n".toCharArray();
    _jsp_string27 = "/resources/bdp/js/bdp/util.js\"></script>\r\n<script src=\"".toCharArray();
    _jsp_string14 = "/resources/bdp/bootstrap/js/bootstrap.min.js\"></script>\r\n<script src=\"".toCharArray();
    _jsp_string29 = "/resources/bdp/bootstrap/export/tableExport.min.js\"></script>\r\n<script src=\"".toCharArray();
    _jsp_string24 = "/resources/bdp/bootstrap/datetimepicker/js/bootstrap-datetimepicker.js\"></script>\r\n<script src=\"".toCharArray();
    _jsp_string34 = "nel-default\">\r\n				<div class=\"panel-heading\">\r\n					<h4 class=\"panel-title\">\r\n						<a data-toggle=\"collapse\"\r\n							style=\"display: block; text-decoration: none;\"\r\n							href=\"#searchWebsite\"> \u641c\u7d22 </a>\r\n					</h4>\r\n				</div>\r\n				<div id=\"searchWebsite\" class=\"panel-collapse collapse\">\r\n					<div class=\"panel-body\">\r\n						<form id=\"Website_searchForm\">\r\n							<div class=\"form-group input-group\">\r\n								<span class=\"input-group-addon\">\u7f51\u7ad9\u540d\u79f0</span> <input type=\"text\"\r\n									class=\"form-control\" style=\"width: 200px;\" name=\"websitename\" />\r\n							</div>\r\n							<div style=\"float: right;\">\r\n								<input type=\"button\" value=\"\u641c\u7d22\" id=\"Website_search\"\r\n									class=\"btn btn-Default\" /> <input type=\"button\" value=\"\u6e05\u7a7a\"\r\n									id=\"Website_searchReset\" class=\"btn btn-Default\" />\r\n							</div>\r\n						</form>\r\n					</div>\r\n				</div>\r\n			</div>\r\n		 <!--\u529f\u80fd\u83dc\u5355 -->\r\n			<div id=\"Website_toolbar\">			\r\n				<input type=\"button\" class=\"btn btn-primary\" value=\"\u65b0\u589e\"\r\n					id=\"Website_add\">\r\n			</div>\r\n			<table id=\"Website_table\">\r\n			</table>\r\n		</div>\r\n		<!--\u7b2c\u4e00\u5c42\u6a21\u6001\u6846  -->\r\n        <div class=\"modal fade\" id=\"Website_Modaladd\" tabindex=\"-1\" role=\"dialog\"\r\n      aria-labelledby=\"Website_ModalTitle\" aria-hidden=\"true\">\r\n      <div class=\"modal-dialog\" style=\"width: 600x;\">\r\n        <div class=\"modal-content\">\r\n          <div class=\"modal-header\">\r\n            <button type=\"button\" class=\"close\" data-dismiss=\"modal\"\r\n              aria-hidden=\"true\">&times;</button>\r\n            <h4 class=\"modal-title\" id=\"Website_ModalTitle\"></h4>\r\n          </div>\r\n          <div class=\"modal-body\">\r\n            <form id=\"Website_addform\" class=\"form-horizontal\">\r\n               <input type='hidden' name='id' id='hiddenaddID'>\r\n               <div class='form-group'>\r\n             <label class='col-sm-3 control-label'><span style='color:red'>*</span>\u96c6\u56e2\u540d\u79f0\uff1a</label>\r\n               <div class='col-sm-9'>\r\n                 <select id=\"sitegroup\" name=\"sitegroupid\" class=\"form-control\" style=\"width:25%;\"></select>\r\n               </div>\r\n             </div>\r\n             <div class='form-group'>\r\n             <label class='col-sm-3 control-label'><span style='color:red'>*</span>\u7f51\u7ad9\u540d\u79f0\uff1a</label>\r\n               <div class='col-sm-9'>\r\n                 <input class='form-control' name='websitename'>\r\n               </div>\r\n             </div>\r\n             <div class='form-group'>\r\n             <label class='col-sm-3 control-label'><span style='color:red'>*</span>\u7f51\u7ad9\u62fc\u97f3\uff1a</label>\r\n               <div class='col-sm-9'>\r\n                 <input class='form-control' name='websitepy'>\r\n               </div>\r\n             </div>\r\n             <div class='form-group'>\r\n             <label class='col-sm-3 control-label'><span style='color:red'>*</span>\u6709\u6548\u6027\uff1a</label>\r\n                <div class='col-sm-9'>\r\n                   <select name='status' class=\"form-control\" style=\"width:25%;\">\r\n                     <option value=\"1\">\u6709\u6548</option>\r\n                     <option value=\"0\">\u65e0\u6548</option>\r\n                     <option value=\"3\">\u5f52\u6863</option>\r\n                   </select>\r\n                   </div>\r\n             </div>\r\n\r\n             <div class='form-group'>\r\n             <label class='col-sm-3 control-label'>\u5907\u6ce8\uff1a</label>\r\n               <div class='col-sm-9'>\r\n                 <input class='form-control' name='memo'>\r\n               </div>\r\n             </div>\r\n             \r\n                                                                                  \r\n            </form>\r\n          </div>\r\n          <div class=\"modal-footer\">\r\n            <button type=\"button\" class=\"btn btn-default\" data-dismiss=\"modal\"\r\n              id=\"Website_close\">\u5173\u95ed</button>\r\n            <button type=\"button\" class=\"btn btn-primary\" id=\"Website_addsubmit\">\u63d0\u4ea4</button>\r\n          </div>\r\n        </div>\r\n      </div>\r\n    </div>\r\n		<div class=\"modal fade\" id=\"Website_Modalmodify\" tabindex=\"-1\" role=\"dialog\"\r\n			aria-labelledby=\"Website_ModalTitle\" aria-hidden=\"true\">\r\n			<div class=\"modal-dialog\" style=\"width: 600x;\">\r\n				<div class=\"modal-content\">\r\n					<div class=\"modal-header\">\r\n						<button type=\"button\" class=\"close\" data-dismiss=\"modal\"\r\n							aria-hidden=\"true\">&times;</button>\r\n						<h4 class=\"modal-title\" id=\"Website_ModalTitle\"></h4>\r\n					</div>\r\n					<div class=\"modal-body\">\r\n						<form id=\"Website_modifyform\" class=\"form-horizontal\">\r\n						   <input type='hidden' name='id' id='hiddenmodifyID'>\r\n             <div class='form-group'>\r\n             <label class='col-sm-3 control-label'><span style='color:red'>*</span>\u96c6\u56e2id\uff1a</label>\r\n               <div class='col-sm-9'>\r\n                 <input class='form-control' name='sitegroupid' readonly=\"readonly\">\r\n               </div>\r\n             </div>\r\n             <div class='form-group'>\r\n             <label class='col-sm-3 control-label'><span style='color:red'>*</span>\u96c6\u56e2\u62fc\u97f3\uff1a</label>\r\n               <div class='col-sm-9'>\r\n                 <input class='form-control' name='sitegrouppy' readonly=\"readonly\">\r\n               </div>\r\n             </div>\r\n             <div class='form-group'>\r\n             <label class='col-sm-3 control-label'><span style='color:red'>*</span>\u96c6\u56e2\u540d\u79f0\uff1a</label>\r\n               <div class='col-sm-9'>\r\n                 <input class='form-control' name='sitegroupname' readonly=\"readonly\">\r\n               </div>\r\n             </div>  \r\n             <div class='form-group'>\r\n             <label class='col-sm-3 control-label'><span style='color:red'>*</span>\u7f51\u7ad9\u540d\u79f0\uff1a</label>\r\n               <div class='col-sm-9'>\r\n                 <input class='form-control' name='websitename'>\r\n               </div>\r\n             </div>\r\n             <div class='form-group'>\r\n             <label class='col-sm-3 control-label'><span style='color:red'>*</span>\u7f51\u7ad9\u62fc\u97f3\uff1a</label>\r\n               <div class='col-sm-9'>\r\n                 <input class='form-control' name='websitepy'>\r\n               </div>\r\n             </div>\r\n             <div class='form-group'>\r\n             <label class='col-sm-3 control-label'><span style='color:red'>*</span>\u6709\u6548\u6027\uff1a</label>\r\n               <div class='col-sm-9'>\r\n                   <select name='status' id=\"status\" class=\"form-control\" style=\"width:20%;\">\r\n                     <option value=\"1\">\u6709\u6548</option>\r\n                     <option value=\"0\">\u65e0\u6548</option>\r\n                     <option value=\"2\">\u5f52\u6863</option>\r\n                   </select>\r\n               </div>\r\n             </div>\r\n\r\n             <div class='form-group'>\r\n             <label class='col-sm-3 control-label'>\u5907\u6ce8\uff1a</label>\r\n               <div class='col-sm-9'>\r\n                 <input class='form-control' name='memo'>\r\n               </div>\r\n             </div>\r\n						</form>\r\n					</div>\r\n					<div class=\"modal-footer\">\r\n						<button type=\"button\" class=\"btn btn-default\" data-dismiss=\"modal\"\r\n							id=\"Website_close\">\u5173\u95ed</button>\r\n						<button type=\"button\" class=\"btn btn-primary\" id=\"Website_modifysubmit\">\u63d0\u4ea4</button>\r\n					</div>\r\n				</div>\r\n			</div>\r\n		</div>\r\n\r\n		<div class=\"modal fade\" id=\"Website_view_Modal\" tabindex=\"-1\"\r\n			role=\"dialog\" aria-labelledby=\"Website_view_ModalTitle\"\r\n			aria-hidden=\"true\">\r\n			<div class=\"modal-dialog\" style=\"width: 580x;\">\r\n				<div class=\"modal-content\">\r\n					<div class=\"modal-header\">\r\n						<button type=\"button\" class=\"close\" data-dismiss=\"modal\"\r\n							aria-hidden=\"true\">&times;</button>\r\n						<h4 class=\"modal-title\" id=\"Website_view_ModalTitle\">\u67e5\u770b\u7f51\u7ad9\u8be6\u60c5</h4>\r\n					</div>\r\n					<div class=\"modal-body\">\r\n						<form id=\"Website_view_form\" class=\"form-horizontal\">\r\n                         <div class='form-group'> \r\n             <label class='col-sm-3 control-label'>\u96c6\u56e2id\uff1a</label> \r\n               <div class='col-sm-9'> \r\n                 <label name='sitegroupid' style='padding-top: 6px;'></label> \r\n               </div> \r\n             </div> \r\n             <div class='form-group'> \r\n             <label class='col-sm-3 control-label'>\u96c6\u56e2\u62fc\u97f3\uff1a</label> \r\n               <div class='col-sm-9'> \r\n                 <label name='sitegrouppy' style='padding-top: 6px;'></label> \r\n               </div> \r\n             </div> \r\n							             <div class='form-group'> \r\n             <label class='col-sm-3 control-label'>\u7f51\u7ad9id\uff1a</".toCharArray();
    _jsp_string11 = "/resources/bdp/js/jquery/jquery.min.js\"></script>\r\n<!--highChart  -->\r\n<script src=\"".toCharArray();
    _jsp_string23 = "/resources/bdp/bootstrap/js/bootstrap-multiselect.js\"></script>\r\n<!--\u65f6\u95f4\u63a7\u4ef6  -->\r\n<script src=\"".toCharArray();
    _jsp_string17 = "/resources/bdp/js/bootstrap-table/bootstrap-table-zh-CN.min.js\"></script>\r\n<script src=\"".toCharArray();
    _jsp_string2 = "\";\r\n</script>\r\n\r\n<link href=\"".toCharArray();
    _jsp_string9 = "/resources/bdp/bootstrap/css/summernote.css\" rel=\"stylesheet\">\r\n<!--  bootstrap \u56fe\u6807-->\r\n<link href=\"".toCharArray();
    _jsp_string33 = "\";\r\nfunction setSelect(id,obj,type){	 \r\n	if($(id).val()==null){\r\n	 	var temp = \"\";\r\n		for(var i=0; i<obj.length; i++){\r\n			if(type == \"sitegroup\"){\r\n				temp += \"<option value='\"+obj[i].id+\"'>\"+obj[i].sitegroupname+\"</option>\";\r\n			}\r\n			\r\n		}\r\n		$(id).html(temp);\r\n	} \r\n}\r\n\r\n$(function(){\r\n	 ajaxHander(contextPath + \"/Website/sitegrouplist.do\",{},\"POST\");  \r\n});\r\nfunction operateFormatter(value, row, index) {\r\n    return [\r\n            '&nbsp;<a class=\"check\"  href=\"javascript:void(0)\" title=\"\u67e5\u770b\">',\r\n            '<i class=\"glyphicon glyphicon-link\"></i>',\r\n            '</a> &nbsp; ',\r\n            '  <a class=\"edit\" href=\"javascript:void(0)\" title=\"\u7f16\u8f91\">',\r\n            '<i class=\"glyphicon glyphicon-edit\"></i>',\r\n            '</a> &nbsp; ',\r\n            ' <a class=\"delete\"  href=\"javascript:void(0)\" title=\"\u5220\u9664\">',\r\n            '<i class=\"glyphicon glyphicon-remove-circle\"></i>',\r\n            '</a>'\r\n    ].join('');\r\n}\r\n\r\nwindow.operateEvents = {\r\n    'click .edit': function (e, value, row, index) {\r\n	    object_operate.show_modify_modal(row);\r\n    },\r\n    'click .check': function (e, value, row, index) {\r\n    	object_operate.show_view_modal(row);\r\n    },\r\n    'click .delete': function (e, value, row, index) {\r\n    	object_operate.del();\r\n   }\r\n};\r\nglobal_variable.model = \"Website\";\r\nglobal_variable.model_cn = \"\u7f51\u7ad9\u7ba1\u7406\";\r\nglobal_variable.sortName=\"\";\r\nglobal_variable.columns =\r\n[ \r\n {field: 'state',checkbox:true},\r\n {field: 'id',title: 'id',formatter: function(value,row,index){return row.id;}}, \r\n {field: 'websitename',title: '\u7f51\u7ad9\u540d\u79f0',formatter: function(value,row,index){return row.websitename;}}, \r\n {field: 'websitepy',title: '\u7f51\u7ad9\u62fc\u97f3',formatter: function(value,row,index){return row.websitepy;}}, \r\n {field: 'sitegroupid',title: '\u96c6\u56e2id',formatter: function(value,row,index){return row.sitegroupid;}},\r\n {field: 'sitegroupname',title: '\u96c6\u56e2\u540d\u79f0',formatter: function(value,row,index){return row.sitegroupname;}}, \r\n {field: 'sitegrouppy',title: '\u96c6\u56e2\u62fc\u97f3',formatter: function(value,row,index){return row.sitegrouppy;}}, \r\n {field: 'status',title: '\u72b6\u6001',formatter: function(value,row,index){\r\n	 if(row.status == 0)\r\n		 return '\u65e0\u6548';\r\n	 else if(row.status == 1)\r\n		 return '\u6709\u6548';\r\n	 else if(row.status == 2)\r\n		 return '\u5f52\u6863';\r\n	 }\r\n\r\n }, \r\n {field: 'addtime',title: '\u6dfb\u52a0\u65f6\u95f4',formatter: function(value,row,index){return row.addtime;}}, \r\n {field: 'addperson',title: '\u6dfb\u52a0\u4eba',formatter: function(value,row,index){return row.addperson;}}, \r\n {field: 'memo',title: '\u5907\u6ce8',formatter: function(value,row,index){return row.memo;}}, \r\n	\r\n /* {field: 'creatDate',sortable:true,title: '\u521b\u5efa\u65f6\u95f4',formatter: function(value,row,index){\r\n	 return  dateFormat(row.creatDate, 'yyyy-MM-dd HH:mm:ss');}}, */\r\n {field: 'operate',title: '\u64cd\u4f5c',events: operateEvents,formatter: operateFormatter}\r\n]		\r\nglobal_variable.onLoadSuccess=function(result){\r\n}\r\nglobal_variable.showExport = false;\r\nobject_operate.del=function(){\r\n	var rows = $(\"#\"+global_variable.model+\"_table\").bootstrapTable(\"getSelections\");\r\n	var ids = [];\r\n	//for( var o in rows) {\r\n		//ids.push(rows[o].id);\r\n	//}\r\n	for( var o in rows) {		\r\n		var k=0;//\u83b7\u53d6\u7b2c\u4e00\u5217\r\n		for(var i in rows[o]){\r\n			if(k==0){\r\n				ids.push(rows[o][i]);\r\n			}\r\n			k++;\r\n		}\r\n	}\r\n	\r\n	if(rows.length > 0){\r\n		if(confirm(\"\u8bf7\u786e\u8ba4\u662f\u5426\u6279\u91cf\u5220\u9664\"+global_variable.model_cn)) {\r\n			  $.ajax({\r\n					 async : false,\r\n					 type: 'POST',\r\n			         data:JSON.stringify(ids),\r\n					 url:\"delete\"+object_name+\".do\",\r\n					 dataType:\"json\",\r\n					 contentType:\"application/json\",\r\n					 error: function (result) {//\u8bf7\u6c42\u5931\u8d25\u5904\u7406\u51fd\u6570\r\n						 alert(\"\u8bf7\u6c42\u5931\u8d25\");\r\n					  },\r\n					 success:function(result){ //\u8bf7\u6c42\u6210\u529f\u540e\u5904\u7406\u51fd\u6570\u3002 \r\n						alert(result.msg);\r\n						object_operate.success();\r\n					 }\r\n				 }); \r\n		}	\r\n	}else {\r\n		alert(\"\u8bf7\u5148\u9009\u62e9\u5220\u9664\u884c\");\r\n	}\r\n}\r\nobject_operate.show_add_modal=function(){\r\n	$(\"#\"+global_variable.model+\"_submit\").removeAttr('disabled');\r\n	this.type = \"add\";\r\n	object_form_operate.setTitle();\r\n	$(\"#hiddenaddID\").val(0);//\u8bbe\u7f6e\u9ed8\u8ba4\u503c\r\n	object_form_operate.reset();\r\n	$(\"#\"+global_variable.model+\"_Modaladd\").modal(\"show\");\r\n	$(\"#\"+global_variable.model+\"_addsubmit\").unbind(\"click\");\r\n	$(\"#\"+global_variable.model+\"_addsubmit\").bind(\"click\",function(){\r\n		object_operate.add();\r\n		});	\r\n}\r\nobject_operate.add=function(){\r\n        var data= checkFormadd();\r\n        if(data!=undefined){\r\n        	ajaxHander(\"add\"+object_name+\".do\",data,\"POST\"); \r\n        }\r\n}\r\nobject_operate.show_modify_modal=function(obj){	\r\n	$(\"#\"+global_variable.model+\"_submit\").removeAttr('disabled');\r\n	this.type = \"modify\";\r\n	object_form_operate.setTitle();\r\n	object_form_operate.setValue(obj);\r\n	$(\"#\"+global_variable.model+\"_Modalmodify\").modal(\"show\");\r\n	$(\"#\"+global_variable.model+\"_modifysubmit\").unbind(\"click\");\r\n	$(\"#\"+global_variable.model+\"_modifysubmit\").bind(\"click\",function(){\r\n		object_operate.modify();\r\n		});\r\n	$(\"#\"+global_variable.model+\"_close\").bind(\"click\",function(){\r\n		});\r\n}\r\nobject_operate.modify=function(){	\r\n	 var data= checkFormmodify();\r\n	 if(data!=undefined){\r\n		 ajaxHander(\"modify\"+object_name+\".do\",data,\"POST\"); \r\n	 }\r\n}\r\nobject_form_operate.setValue=function(obj){	\r\n	var k=0;//\u83b7\u53d6\u7b2c\u4e00\u5217\r\n	for(var i in obj){\r\n		if(k==0){\r\n			$(\"#hiddenmodifyID\").val(obj[i]);//\u8bbe\u7f6e\u4fee\u6539\u4e3b\u952e\r\n		}\r\n		k++;\r\n	}\r\n	\r\n	for(var o in obj) {\r\n		if($(\"#\"+global_variable.model+\"_modifyform input[name='\"+o+\"']\").attr('type') == 'radio'){\r\n			$(\"#\"+global_variable.model+\"_modifyform input[value='\"+obj[o]+\"']\").attr(\"checked\",\"checked\");\r\n		}\r\n		   if($(\"#\"+global_variable.model+\"_modifyform input[name='\"+o+\"']\").attr('type') == 'text'||\r\n				   $(\"#\"+global_variable.model+\"_modifyform input[name='\"+o+\"']\").attr('type') == undefined||$(\"#\"+global_variable.model+\"_modifyform input[name='\"+o+\"']\").attr('type') == 'hidden'){\r\n				   if(o==\"status\"){\r\n					   if(obj[o]==0){\r\n						   $(\"#status\").find(\"option[value=0]\").attr(\"selected\",true);\r\n					   }else if(obj[o]==1){\r\n						   $(\"#status\").find(\"option[value=1]\").attr(\"selected\",true);\r\n					   }else{\r\n						   $(\"#status\").find(\"option[value=2]\").attr(\"selected\",true); \r\n					   }\r\n				   }else{\r\n					   $(\"#\"+global_variable.model+\"_modifyform input[name='\"+o+\"']\").val(obj[o]);\r\n				   }\r\n				 \r\n				}\r\n	}\r\n}\r\nobject_form_operate.setViewValue=function(obj,parentName){\r\n	if(!parentName) {\r\n		parentName = \"\";\r\n	}else {\r\n		parentName += \".\";\r\n	}\r\n    for(var o in obj) {\r\n    	$(\"#\"+global_variable.model+\"_view_form label[name='\"+parentName+o+\"']\").text(obj[o]);\r\n		if(obj[o] instanceof Object) {\r\n			object_form_operate.setViewValue(obj[o],parentName+o);\r\n		}\r\n	}\r\n}\r\nobject_form_operate.reset=function(){\r\n	$(\"#\"+global_variable.model+\"_addform\")[0].reset();\r\n	$(\"#\"+global_variable.model+\"_addform input[type='hidden']\").val(\"\");\r\n}\r\nglobal_variable.height=550;\r\n\r\nfunction ajaxHander(url,data,type,value){\r\n	   $.ajax({\r\n			 async : false,\r\n			 type: type,\r\n			 beforeSend: function(){				 \r\n				    },\r\n	         data:data,\r\n			 url:url,\r\n			 success:function(data){ //\u8bf7\u6c42\u6210\u529f\u540e\u5904\u7406\u51fd\u6570\u3002\r\n				 if(url.indexOf(\"sitegrouplist\")>0){\r\n					 setSelect(\"#sitegroup\",data,\"sitegroup\");//\u8bbe\u7f6e\u5185\u5bb9\r\n					 setMultiselect(\"#sitegroup\");//\u521d\u59cb\u5316\u591a\u9009\r\n				 }\r\n				 else{\r\n					 alert(data.msg);\r\n					 object_operate.success();\r\n				 }\r\n			 }\r\n		 }); \r\n}\r\n	\r\nfunction checkFormadd(){		\r\n	    //\u9a8c\u8bc1\r\n		return $(\"#\"+global_variable.model+\"_addform\").serialize();		\r\n}\r\nfunction checkFormmodify(){		\r\n    //\u9a8c\u8bc1\r\n	return $(\"#\"+global_variable.model+\"_modifyform\").serialize();		\r\n}\r\nfunction dateFormat(time,format){\r\n	   var t = new Date(time);\r\n var tf = function(i){return (i < 10 ? '0' : '') + i};\r\n return format.replace(/yyyy|MM|dd|HH|mm|ss/g, function(a){\r\n     switch(a){\r\n         case 'yyyy':\r\n             return tf(t.getFullYear());\r\n             break;\r\n         case 'MM':\r\n             return tf(t.getMonth() + 1);\r\n             break;\r\n         case 'mm':\r\n             return tf(t.getMinutes());\r\n             break;\r\n         case 'dd':\r\n             return tf(t.getDate());\r\n             break;\r\n         case 'HH':\r\n             return tf(t.getHours());\r\n             break;\r\n         case 'ss':\r\n             return tf(t.getSeconds());\r\n             break;\r\n     }\r\n })\r\n}\r\n</script>\r\n</head>\r\n<body>\r\n	<div class=\"container\">\r\n		<div class=\"row\">\r\n			<br>\r\n			<div class=\"panel pa".toCharArray();
    _jsp_string20 = "/resources/bdp/js/jquery/jquery.ztree.core-3.5.min.js\"></script>\r\n<script src=\"".toCharArray();
    _jsp_string3 = "/resources/bdp/css/zTreeStyle/zTreeStyle.css\" rel=\"stylesheet\">\r\n<link href=\"".toCharArray();
    _jsp_string22 = "/resources/bdp/js/jquery/jquery.ztree.exedit-3.5.min.js\"></script>\r\n<!--\u4e0b\u62c9\u9009\u62e9\u6846JS  -->\r\n<script src=\"".toCharArray();
    _jsp_string12 = "/resources/bdp/highchart/highcharts.js\"></script>\r\n<script src=\"".toCharArray();
    _jsp_string25 = "/resources/bdp/bootstrap/datetimepicker/js/bootstrap-datetimepicker.fr.js\"></script>\r\n<!--\u5728\u7ebf\u7f16\u8f91\u5668  -->\r\n<script src=\"".toCharArray();
    _jsp_string1 = "\r\n<script type=\"text/javascript\">\r\n	var contextPath=\"".toCharArray();
    _jsp_string10 = "/resources/bdp/bootstrap/bower_components/font-awesome/css/font-awesome.min.css\" rel=\"stylesheet\">\r\n\r\n<script src=\"".toCharArray();
    _jsp_string16 = "/resources/bdp/js/bootstrap-table/bootstrap-table-resize.js\"></script>\r\n\r\n<script src=\"".toCharArray();
    _jsp_string31 = "/resources/bdp/bootstrap/export/bootstrap-table-export.js\"></script> \r\n<script src=\"".toCharArray();
    _jsp_string8 = "/resources/bdp/bootstrap/css/bootstrap-3.0.3.min.css\" rel=\"stylesheet\" media=\"screen\">\r\n<!--  \u5728\u7ebf\u7f16\u8f91\u5668-->\r\n<link href=\"".toCharArray();
  }
}