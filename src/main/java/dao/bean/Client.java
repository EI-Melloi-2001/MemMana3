package dao.bean;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;  //

/*
 * 解释器模式角色
 * 角色1：环境类Context，封装待解释的表达式（包括变量键值对），提供通过AbstractExpression计算表达式值的方法。
 * 角色2：抽象表达式AbstractExpressionion，定义了抽象方法interpret(Context)。
 * 角色3：终结符表达式TerminalExpression，作为AbstractExpressionion的子类。
 * 角色4：非终结符表达式NonterminalExpression，作为AbstractExpressionion的子类。
 * 
 * 要点：
 * （1）除了定义抽象数据栈Stack<AbstractExpression> numStack外，增加了操作符栈 opStack。
 * （2）入栈和出栈操作，需要考虑运算符的优先级。
 * （3）numStack和opStack根据运算符的优先级，动态、交互式地建立（难点）。
 * 
 *  功能：做若干整数的加、减、乘、除运算，如10+24/3-4*5+6=((10+8)-20)+6=4
 *        AST...后序遍历结果：10 24 3 / + 4 5 * - 6 +
 *        计算方法：对后序遍历结果，自左向右，遇到运算符，就做运算符左边2个数的运算
 */

class Context{  //角色1：环境类Context
	
	private AbstractExpression express;  //表达式语法树

	public void setExpress(AbstractExpression express) {
		this.express = express;
	}
	
	public int run() { 
		return express.interpret(this);
	}
}

abstract class AbstractExpression{ //角色2：抽象表达式AbstractExpressionion
	public abstract int interpret(Context context);  //解释
}

class TerminalExpression extends AbstractExpression{   //终结符表达式TerminalExpression
	
	private String key;  //单个变量表达式
	
	public TerminalExpression(String key) {  //构造器
		this.key = key;
	}

	@Override
	public int interpret(Context context) {  //实现抽象方法
		return Integer.parseInt(key);  //返回变量名的值
	}
}

abstract class NonterminalExpression extends AbstractExpression{  //角色4：非终结符表达式NonterminalExpression
	
	protected AbstractExpression left;
	protected AbstractExpression right;
	
	public NonterminalExpression(AbstractExpression left, AbstractExpression right) { //构造方法
		this.left = left;
		this.right = right;
	}
	//抽象类NonterminalExpression继承抽象类AbstractExpression，不必重写父类的抽象方法
}

class AddAbstractExpressionion extends NonterminalExpression{  //加法解析器
	public AddAbstractExpressionion(AbstractExpression left, AbstractExpression right) {  //构造器
		super(left, right);  //调用父类构造方法
	}
	
	@Override
	public int interpret(Context context) {  //加法实现
		return super.left.interpret(context)+super.right.interpret(context);
	}
}

class SubAbstractExpressionion extends NonterminalExpression{  //减法实现
	public SubAbstractExpressionion(AbstractExpression left, AbstractExpression right) {  //构造器
		super(left, right);
	}
	
	@Override
	public int interpret(Context context) {
		return super.left.interpret(context)-super.right.interpret(context);
	}
}

class MulAbstractExpressionion extends NonterminalExpression{  //乘法实现
	public MulAbstractExpressionion(AbstractExpression left, AbstractExpression right) {  //构造器
		super(left, right);
	}
	
	@Override
	public int interpret(Context context) {
		return super.left.interpret(context)*super.right.interpret(context);
	}
}

class DivAbstractExpressionion extends NonterminalExpression{  //除法实现
	public DivAbstractExpressionion(AbstractExpression left, AbstractExpression right) {  //构造器
		super(left, right);
	}
	
	@Override
	public int interpret(Context context) {
		return super.left.interpret(context)/super.right.interpret(context);
	}
}

public class Client {
	
	public static void main(String[] args) throws NumberFormatException, Exception {
		
		System.out.print("请输入计算表达式：");
		String expStr=(new BufferedReader(new InputStreamReader(System.in))).readLine(); 

		AbstractExpression express=getAST(expStr);  //获取抽象语法树AST
		//在下面设置断点，查看对象express的存储结构，已经处理了优先级的计算顺序对应于AST的后序遍历结果
		
		Context context = new Context();  //创建环境类对象
		context.setExpress(express);  //传递AST
		System.out.println("运算结果："+expStr+"="+context.run());  //计算并输出
	}
	
	private static AbstractExpression getAST(String expStr) throws Exception {
		
		//分离出数据；+和*是Java正则处理的特殊字符，因此需要使用转义符。
		String[] nums = expStr.split("\\+|-|\\*|/"); 
		System.out.println("测试参加运算的整数："+Arrays.toString(nums));
		
		Queue<String> opQueue = new LinkedList<String>();  //创建存取运算符队列
		for(int i=0;i<expStr.length();i++) {  //从运算表达式里取运算符
			String temp=expStr.substring(i,i+1);  //取一个字符
			if(temp.equals("+")||temp.equals("-")||temp.equals("*")||temp.equals("/")) {
				opQueue.add(temp);  //加到队列opQueue
			}
		}
		System.out.println("测试参加运算的运算符队列："+opQueue);  //先进先出
		
		List<String> ASTElementList = new ArrayList<String>();   //创建抽象语法树元素列表
		for(String string : nums) {
			ASTElementList.add(string);
			if(!opQueue.isEmpty()) {
				ASTElementList.add(opQueue.poll());  //从列取出、添加至列表
			}
		}
		System.out.println("测试抽象语法树元素列表："+ASTElementList);
		
		Stack<AbstractExpression> numStack = new Stack<>(); //创建抽象操作数栈      
		Stack<String> opStack  = new Stack<>();  //创建操作符栈 
		//初始栈顶为null(级别为0)，是为了遍历过程能正常结束（结束标志）。
		opStack.push(null);  //null对应的运算优先级为0；+-优先级为1；*/优先级为2
		
		for(String str:ASTElementList) {  //形成语法树AST，优先级高的位于叶子结点
			if("+".equals(str)||"-".equals(str)||"*".equals(str)||"/".equals(str)) {
				 //遇到运算符时，需要与opStack栈顶元素比较。
				 while(getPriority(str)<=getPriority(opStack.peek())) { //peek()是获取栈顶元素
					 //当顶元素的优先级>=运算符的优先级时，弹栈并计算。其中=表示同级运算符先出现先运算
					 AbstractExpression right = numStack.pop();
					 AbstractExpression left = numStack.pop();
					 String op=opStack.pop();  //出栈操作改变栈顶元素（优先级）
                     numStack.push(calculate(left,right,op));  //将运算结果入数据栈
                 }
				 //运算符总是要入栈的，在此之前，已经解决了优先级与同级。
                 opStack.push(str);  //操作符入栈，在此之前，opStack除了null外，最多可以包含2个运算符。
			}else {  
				//遇到操作数时，创建终结表达式并压入numStack栈
				numStack.push(new TerminalExpression(str));
            }
		}
		
		//遍历结束后，符号栈只有2个元素，其中一个为null且位于栈底，用于结束解释。
		while(opStack.peek()!=null) {  
			AbstractExpression right=numStack.pop();
			AbstractExpression left=numStack.pop();
	        numStack.push(calculate(left,right,opStack.pop()));  
	    }
		
		return numStack.pop();  //能否以某种形式输出处理了优先级的AST
	}
	
	private static int getPriority(String s) throws Exception {  //获取优先级
        if(s==null) {  //特殊元素
        	return 0;  //设定优先级
        }
		switch (s) {
		case "+":;
		case "-":
			return 1;    //设定优先级
		case "*":;
		case "/":
			return 2;  //设定优先级
		default:
			break;
		}
        throw new Exception("illegal opStack!");
    }
	
	private static AbstractExpression calculate(AbstractExpression left, AbstractExpression right, String op) throws Exception {
        switch (op) {
        case "+":
            return new AddAbstractExpressionion(left, right);  //加法
        case "-":
            return new SubAbstractExpressionion(left, right);  //减法
        case "*":
            return new MulAbstractExpressionion(left, right);  //乘法
        case "/":
            return new DivAbstractExpressionion(left, right);  //除法
        default:
            break;
        }
        throw new Exception("illegal opStack!");
    }
}