package calculator;

import java.util.*;

public class Calculator
{
	//单项式类
	private static class Monomial
	{
		//表达式中的元素
		Double coefficient;//系数
		HashMap<String, Integer> vars;//变量，保存为变量名->次数的映射
		
		Monomial(String monomial)
		{
			coefficient = 1.0;
			vars = new HashMap<String, Integer>();
			
			String var;//变量名
			int index;//指数
			
			if(monomial.charAt(0) == '-')//处理负号
			{
				coefficient = -1.0;
				monomial = monomial.substring(1);
			}
			String[] factors = monomial.split("\\*|(?<=[0-9])(?=[A-Za-z])|(?<=[A-Za-z])(?=[0-9])");
			/*正常来讲，factor只能有以下几种情况：
			 *1、变量，如x，boo
			 *2、数字，如1, 12，15.2
			 *3、幂式，如x^3，value^5
			 */
			if(factors.length == 1)
				;//TODO 异常处理
			else
			{
				for (String factor : factors)
				{
					if(factor.matches("^[A-Za-z]+$"))//变量
					{
						vars.put(factor,(((vars.get(factor)==null) ? 0 : (vars.get(factor)))+1));
						
					}
					else if(factor.matches("^[0-9]+(\\.[0-9]+)?$"))//数字
					{
						coefficient *= Double.valueOf(factor);
					}
					else if(factor.matches("^[A-Za-z]+\\^[0-9]+$"))//幂式
					{
						String[] pair = factor.split("(?<=[A-Za-z]+)\\^(?=[1-9][0-9]*)");
						if(pair.length == 1)
							;//TODO 异常处理
						else
						{
							if (pair[0].matches("^[A-Za-z]+$"))
								var = pair[0];
							else if(pair[1].matches("^[0-9]+$"))
								index = Integer.valueOf(pair[1]);
							else
								return;//TODO 异常处理
							vars.put(var,(((vars.get(var)==null) ? 0 : (vars.get(var)))+index));//忽略这个错误
						}
						
					}
					else
					{
						;//TODO 异常处理
					}
				}
			}
		}
		
	}
	//将字符串表达式转换成自定义数据类型
	static ArrayList<Monomial> expression(String input)
	{
		ArrayList <Monomial> exp = new ArrayList <Monomial> ();//表达式
		//TODO 把判断条件放这里 @李启飞
		String fixedInput = input.replaceAll("(?<=[+\\-*^])\\s+(?=[+\\-*^])","");
		String[] monomials = fixedInput.split("(?=\\+|-)");
		for(String monomial : monomials)
		{
			exp.add(new Monomial(monomial));
		}
		return exp;
	}
	//按照输入的值对表达式进行计算
	//注意加法的处理
	static void simplify(ArrayList <Monomial> exp, String input)
	{
		String[] assigns = input.substring(9).split("\\s+");
		String var;
		Double value;
		//TODO 空串的处理
		for(String assign : assigns)//得到单个赋值表达式
		{
			String[] temp = assign.split("=");
			if(temp.length == 1)
			{
				//TODO 此时应当报错
			}
			else
			{
				for(int i = 0;i < temp.length;)
				{
					if(temp[i].matches("^[A-Za-z]+$"))
					{
						var = temp[i];
						i++;
					}
					else if(temp[i].matches("^[0-9]+(\\.[0-9]+)?$"))
					{
						value = Double.valueOf(temp[i]);
						i++;
					}
					else
					{
						//TODO 是否需要异常处理？
						i++;
					}
					
				}
			}
		}
	}
	//对表达式求导
	static void derivative(ArrayList <Monomial> exp, String input)
	{
		//TODO 补全这个函数
		
	}
	
	public static void main(String[] args)
	{
		//ArrayList <Monomial> exp;
		Scanner scan = new Scanner(System.in);
		while(true)
		{
			String input = scan.nextLine();
			if(input.equals("!"))
			{
				scan.close();
				System.exit(0);
			}
			else
			{
				System.out.println(input.matches("^[\\s]*[-]?[\\s]*(([\\.])([0-9]+))*(([a-zA-Z]+)([\\^])([1-9][0-9]*))*[A-Za-z0-9]+[A-Za-z0-9]*(([0-9]+)(([\\s]*)([*])([\\s]*)([A-Za-z0-9]+))*(([\\s]*)([+-])([\\s]*)(([a-zA-Z]+)([\\^])([1-9][0-9]*)*(([\\.])([0-9]+))*)*([A-Za-z0-9])+(([\\s]*)([*])([\\s]*)([A-Za-z0-9]+))*)*[\\s]*"));//处理表达式);;
				//System.out.println(input.matches("^[\\s]*[-]*[\\s]*{0,1}[A-Za-z0-9]+[A-Za-z0-9]*(([\\s]*)([*^])([\\s]*)([A-Za-z0-9]+))*(([\\s]*)([+-])([\\s]*)([A-Za-z0-9])+(([\\s]*)([*^])([\\s]*)([A-Za-z0-9]+))*)*[\\s]*"));
				/*String temp = input.substring(9);
				String[] splitedInputs = temp.split("\\s+");
				for(String monomial:splitedInputs)
				{
					System.out.println(monomial);
				}*/
			}
		}
		/*while(true)
		{
			if(scan.hasNextLine())
			{
				String input = scan.nextLine();
				if(input.matches("^[\\s]*[-]*[\\s]*{0,1}[A-Za-z0-9]+[A-Za-z0-9]*(([\\s]*)([*^])([\\s]*)([A-Za-z0-9]+))*(([\\s]*)([+-])([\\s]*)([A-Za-z0-9])+(([\\s]*)([*^])([\\s]*)([A-Za-z0-9]+))*)*"))//处理表达式
				{
					exp = expression(input);
				}
				else if(input.matches("!simplify[ A-Za-z0-9=]+$") )//处理运算命令
				{
					simplify(exp,input);
				}
				else if(input.matches("!d\\/d[A-Za-z]+$"))//处理求导命令
				{
					derivative(exp,input);
				}
				else
				{
					//TODO 异常
				}
			}
			
		}*/
	}

}
