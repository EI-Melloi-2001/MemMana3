package dao.bean;
import java.sql.ResultSet;
import javax.servlet.http.HttpServletRequest;

public class Pager {
    private ResultSet rs;  // 当前页记录
    private Integer recordsNum;  //总记录数
    private int pageSize;  //每页记录数，值类型，必须指定
    private Integer page;  //当前页序号
    private Integer pages;  //总页数
    private HttpServletRequest request;  //请求对象
    
    public Pager(ResultSet rs,Integer recordsNum,int pageSize,Integer page, HttpServletRequest request) {  //构造方法
        this.rs=rs;
        this.recordsNum=recordsNum;
        this.pageSize = pageSize;
        this.page = page;
        this.pages = recordsNum % pageSize == 0 ? recordsNum / pageSize:recordsNum/pageSize+1;
        this.request = request;
    }
    
    public String getPageNav() {  //返回导航条
    	return pageNav();   //本属性与其他属性相关联
    }
    
    public String pageNav(){  //导航条实现
        return  "&nbsp;"+getFirstPage()+
                "&nbsp;|&nbsp;"+getUpPage()+
                "&nbsp;|&nbsp;"+getDownPage()+
                "&nbsp;|&nbsp;"+getLastPage()+
                " 共"+recordsNum +"条记录&nbsp;|"+
                "&nbsp;&nbsp;页：<font color='red'>"+page+"</font>/"+pages+
                "&nbsp; <form method='get' action="+getURLinfo(page)+
                "><input type='text' style='width:30px; height:20px' name='p'/> "+
                "<input type='submit' value='go' class='btn' /></form>";
    }
    private String getFirstPage(){  //获取首页
        if(page<=1){
            return "首页";
        }else{
            return "<a href="+getURLinfo(1)+">首页</a>";
        }
    }
    private String getDownPage(){  //获取下一页
        if(page == pages){
            return "下一页";
        }else{
            return "<a href='"+getURLinfo(page+1)+"'>下一页</a>";
        }
    }
    private String getUpPage(){  //获取上一页
        if(page == 1){
            return "上一页";
        }else{
            return "<a href='"+getURLinfo(page-1)+"'>上一页</a>";
        }
    }
    private String getLastPage(){  //获取最后一页
        if(page>=pages){
            return "尾页";
        }else{
            return "<a href='"+getURLinfo(pages)+"'>尾页</a>";
        }
    }
    
    private String getURLinfo(Integer page){ 
        //String requestPath = request.getServletPath();  //Servlet路径（小）
        String requestPath = request.getRequestURI();  //相对项目根的路径（中）
        //String requestPath = request.getRequestURL();  //带协议和主机名的全路径（大）
        System.out.println("请求路径："+requestPath);
        return requestPath+ "?p="+page;   //构造相对于根站点的URL请求信息
    }
    
    public ResultSet getRs() {  //获取当前页记录
        return rs;
    }
    public void setRs(ResultSet rs) {
        this.rs = rs;
    }
    public int getPageSize() {
        return pageSize;
    }
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
    public Integer getPage() {  //返回当前页
        return page;
    }
    public void setPage(Integer page) {
        this.page = page;
    }
    public Integer getRecordsNum() { //返回总记录数
        return recordsNum;
    }
    public void setRecordsNum(Integer recordsNum) {
        this.recordsNum = recordsNum;
    }
    public Integer getPages() {  //返回总页数
        return pages;
    }
    public void setPages(Integer pages) {
        this.pages = pages;
    }
}
