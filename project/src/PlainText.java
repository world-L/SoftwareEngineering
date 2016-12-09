// PlainText class that contain pure text
// the text in this class should be handling escape character

public class PlainText extends Tokens{

	
	//private String test;

	

	public PlainText(String text){
		super.text = text;
		
		group(text);	
	}

	public void group(String text){
			
			//for htmlText
			if((text.contains("<br>"))||(text.contains("<table>"))
				||(text.contains("</table>"))||(text.contains("<tr>"))||(text.contains("</tr>"))
				||(text.contains("<td>"))||(text.contains("</td>"))||(text.contains("<a>"))||(text.contains("<img>"))){
				setItem(text);
			}

			//for plainText
			if(text.contains(">")){
				convertCh(text);
			}
			if(text.contains("<")){
				convertCh(text);
			}
		    if(text.contains("&")){
				convertCh(text);
			}
			if( text.contains("\\")||text.contains("#")||text.contains("'")||text.contains("{")||text.contains("}")
			||text.contains("[")||text.contains("]")||text.contains("(")
			||text.contains(")")||text.contains(".")||text.contains("!") ){
				convertEscape(text); 
			}
			
			
			//for style text
			if(text.contains("*"))
			{
				int x=text.indexOf("*");
				String temp=text.substring(x+1);
				if(temp.contains("*")){
					convertSt(text);
				}
				else{
						//plain text that escape character
						convertEscape(text);
				}	
			
			}
			if(text.contains("_")){
				int x=text.indexOf("_");
				String temp=text.substring(x+1);
				if((temp.contains("_"))&&(text.charAt(x+1)!=' ')){
					convertSt(text);
				}
				else{
						//plain text that escape character
						convertEscape(text);
				}	
		
			}
			 if(text.contains("**")){
				int x=text.indexOf("**");
				String temp=text.substring(x+2);
				
				if((temp.contains("**"))&&(text.charAt(x+2)!=' ')){
					convertSt(text);
				}	
			}
			 if(text.contains("__")){
				int x=text.indexOf("__");
				String temp=text.substring(x+2);
				
				if((temp.contains("__"))&&(text.charAt(x+2)!=' ')){
					convertSt(text);
				}	
			}

			//for plainText
			else{
				setItem(text);
			}

	}
	
	public void convertCh(String text){
			if(text.contains("&")){
				int a=text.indexOf("&");
				String sub=text.substring(a,a+4);
				if(!sub.equals("&gt;"))
				{	String newText=text.replace("&","&amp;");
					setItem(newText);}
			}else if(text.contains(">")){
				String newText=text.replace(">","&gt;");
				setItem(newText);
			}else if(text.contains("<")){
				String newText=text.replace("<","&lt;");
				setItem(newText);
			}
	}

	public void convertEscape(String text){
			
			String escape="\\";

			if(text.contains("\\")){
				String change = escape+"\\";
				String newText= text.replace("\\",change);
				setItem(newText);	
			}else if(text.contains("*")){
				String change = escape+"*";
				String newText= text.replace("*",change);
				setItem(newText);
			}else if(text.contains("#")){
				String change = escape+"#";
				String newText= text.replace("#",change);
				setItem(newText);	
			}else if(text.contains("'")){
				String change = escape+"'";
				String newText= text.replace("'",change);
				setItem(newText);	
			}else if(text.contains("{")){
				String change = escape+"{";
				String newText= text.replace("{",change);
				setItem(newText);	
			}else if(text.contains("}")){
				String change = escape+"}";
				String newText= text.replace("}",change);
				setItem(newText);	
			}else if(text.contains("[")){
				String change = escape+"[";
				String newText= text.replace("[",change);
				setItem(newText);	
			}else if(text.contains("]")){
				String change = escape+"]";
				String newText= text.replace("]",change);
				setItem(newText);	
			}else if(text.contains("(")){
				String change = escape+"(";
				String newText= text.replace("(",change);
				setItem(newText);	
			}else if(text.contains(")")){
				String change = escape+")";
				String newText= text.replace(")",change);
				setItem(newText);	
			}else if(text.contains(".")){
				String change = escape+".";
				String newText= text.replace(".",change);
				setItem(newText);	
			}else if(text.contains("!")){
				String change = escape+"!";
				String newText= text.replace("!",change);
				setItem(newText);	
			}
	}

	public void convertSt(String text){

		if(text.contains("*")){
			String temp=text.replaceFirst("\\*","<em>");
			String newText=temp.replaceFirst("\\*","</em>");
			setItem(newText);
		}
		if(text.contains("**")){
			String temp=text.replaceFirst("\\*\\*","<strong>");
			String newText=temp.replaceFirst("\\*\\*","</strong>");
			setItem(newText);
		}
		if(text.contains("_")){
			String temp=text.replaceFirst("_","<em>");
			String newText=temp.replaceFirst("_","</em>");
			setItem(newText);
		}
		if(text.contains("__")){
			String temp=text.replaceFirst("__","<strong>");
			String newText=temp.replaceFirst("__","</strong>");
			setItem(newText);
		}

	}

}