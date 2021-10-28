<%@ page language="java"  pageEncoding="UTF-8"%>
<style>
	#sm{
		overflow:hidden; /*隐藏Div中多余的内容*/
		width:800px; height:106px;
		margin: 0 auto;
	}
</style>
     <div id="sm">
            <table><!--外表格1x2，且第2单元格是空的-->
                <tr>
                    <td id="Pic1">
                        <table><!--内表格1x9，存放9张图片-->
                            <tr><td><img src="images/1.jpg" /></td>
                                <td><img src="images/2.jpg" /></td>
                                <td><img src="images/3.jpg" /></td>
                                <td><img src="images/4.jpg" /></td>
                                <td><img src="images/5.jpg" /></td>
                                <td><img src="images/6.jpg" /></td>
                                <td><img src="images/7.jpg" /></td>
                                <td><img src="images/8.jpg" /></td>
								<td><img src="images/9.jpg" /></td></tr></table></td>
                    <td id="Pic2"></td></tr></table></div>
<script type="text/javascript"><!--下面的客户端脚本不可放置在页面头部-->  
	Pic2.innerHTML = Pic1.innerHTML;  //复制一组图片，但被隐藏
    function scrolltoleft() {   //定义向左移动方法
		sm.scrollLeft++;  //改变层的水平坐标，实现向左移动
        if(sm.scrollLeft>=Pic1.scrollWidth)  //需要复位
			sm.scrollLeft=0;  //层的位置复位，浏览器窗口的宽度是有限的
        }
        var MyMar = setInterval(scrolltoleft, 40);   //定时器，方法名后不可加()
        //两面两行是用方法响应对象的事件
		sm.onmouseover=function(){  //匿名方法（函数）
			clearInterval(MyMar); }   //停止滚动
        sm.onmouseout=function() { 
			MyMar = setInterval(scrolltoleft, 40); } //继续滚动
</script>                    