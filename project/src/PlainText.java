// PlainText class that contain pure text
// the text in this class should be handling escape character

public class PlainText extends Tokens
{


	//private String test;



	public PlainText(String text){
		super.text = text;
		group(text);
	}


	public void group(String text){

			String[] tempToken=text.split(" ");
			boolean convert=false;
			for(int a = 0;a<tempToken.length;a++){
			//for htmlText
			if(text.contains("<table>"))
				setItem(text);
			else if(text.contains("</table>"))
				setItem(text);
			else if(text.contains("<tr>"))
				setItem(text);
			else if(text.contains("<td>"))
				setItem(text);
			else if(text.contains("</td>"))
				setItem(text);
			else if(text.contains("<a>"))
				setItem(text);
			else if(text.contains("<img>"))
				setItem(text);
			else if(text.contains("</tr>"))
				setItem(text);
			else if(text.contains("<br>"))
				setItem(text);
			else if((!convert)&&(text.contains("<http://"))){
				int start=text.indexOf("<http");
				String sub=text.substring(start+1);
				if(sub.contains(">"))
				{
					int end=text.indexOf(">");
					String url=text.substring(start+1,end);
					convert=true;
					convertUrl(url);
				}	

			}
			else if((!convert)&&(text.contains("<https://"))){
				int start=text.indexOf("<http");
				String sub=text.substring(start+1);
				if(sub.contains(">"))
				{
					int end=text.indexOf(">");
					String url=text.substring(start+1,end);
					convert=true;
					convertUrl(url);
				}	

			}
			else if((!convert)&&(text.contains("!["))&&(text.contains("](")) ){
				int start=text.indexOf("![");
				String sub=text.substring(start+1);
				if(sub.contains("]("))
				{
					
					int alt=text.indexOf("]");
					int mid=text.indexOf("(");
					int end=text.indexOf(")");
					String alt2=text.substring(start+2,alt);
					String img=text.substring(mid+1,end);
					convert=true;
					String converted="<img src=\""+img+"\" "+"alt=\""+alt2+"\">";
					setItem(converted);
				}	

			}
			else if((!convert)&&(text.contains("["))&&( (text.contains("](http://"))||(text.contains("](https://")) ))
		{
			int start=text.indexOf("[");
			String sub=text.substring(start+1);
			if(sub.contains("]("))
			{
				int alt=text.indexOf("]");
				int mid=text.indexOf("(");
				int end=text.indexOf(")");
				String alt2=text.substring(start+1,alt);
				String url=text.substring(mid+1,end);
				String converted="<a href=\""+url+"\">"+alt2+"</a>";
				convert=true;
				setItem(converted);
				
			}
		}
			 //for plainText
			
			else if(text.contains(">")){
				convertCh(text);
			}
			else if(text.contains("<")){
				convertCh(text);
			}
		    else if(text.contains("&")){
				convertCh(text);
			}

			else if(text.contains("\\\\"))
				convertEscape(text);
			else if(text.contains("\\#"))
				convertEscape(text);
			else if(text.contains("\\'"))
				convertEscape(text);
			else if(text.contains("\\{"))
				convertEscape(text);
			else if(text.contains("\\}"))
				convertEscape(text);
			else if(text.contains("\\["))
				convertEscape(text);
			else if(text.contains("\\]"))
				convertEscape(text);
			else if(text.contains("\\("))
				convertEscape(text);
			else if(text.contains("\\)"))
				convertEscape(text);
			else if(text.contains("\\."))
				convertEscape(text);
			else if(text.contains("\\!"))
				convertEscape(text);
			else if(text.contains("\\*"))
				convertEscape(text);
			else if(text.contains("\\_"))
				convertEscape(text);

			//for style text
			else if(text.contains("*"))
			{
				int x=text.indexOf("*");
				String temp=text.substring(x+1);
				if((temp.contains("*"))&&(text.charAt(x+1)!=' ')){
					convertSt(text+" ");
				}
				else{
						//plain text that escape character
						//convertEscape(text);
					setItem(text);
				}

			}
			else if(text.contains("_")){
				int x=text.indexOf("_");
				String temp=text.substring(x+1);
				if((temp.contains("_"))&&(text.charAt(x+1)!=' ')){
					convertSt(text);
				}
				else{
						//plain text that escape character
					//convertEscape(text);
					setItem(text);
				}

			}
			else if(text.contains("**")){
				int x=text.indexOf("**");
				String temp=text.substring(x+2);

				if((temp.contains("**"))&&(text.charAt(x+2)!=' ')){
					convertSt(text);
				}
				else{
					setItem(text);
				}
			}
			else if(text.contains("__")){
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
}
	

	public void convertCh(String text){
			if(text.contains("&")){
				int a=text.indexOf("&");
				String sub = "";
				
				if(text.length()>3)
					text.substring(a,a+3);

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



			if(text.contains("\\\\")){
				//String change = "\\\\";
				String newText= text.replace("\\\\","\\");
				setItem(newText);
			}else if(text.contains("\\*")){
				//String change = escape+"*";
				String newText= text.replace("\\*","*");
				setItem(newText);
			}else if(text.contains("\\#")){
				//String change = escape+"#";
				String newText= text.replace("\\#","#");
				setItem(newText);
			}else if(text.contains("\\'")){
				//String change = escape+"'";
				String newText= text.replace("\\'","'");
				setItem(newText);
			}else if(text.contains("\\{")){
				//String change = escape+"{";
				String newText= text.replace("\\{","{");
				setItem(newText);
			}else if(text.contains("\\}")){
				//String change = escape+"}";
				String newText= text.replace("\\}","}");
				setItem(newText);
			}else if(text.contains("\\[")){
				//String change = escape+"[";
				String newText= text.replace("\\[","[");
				setItem(newText);
			}else if(text.contains("\\]")){
				//String change = escape+"]";
				String newText= text.replace("\\]","]");
				setItem(newText);
			}else if(text.contains("\\(")){
				//String change = escape+"(";
				String newText= text.replace("\\(","(");
				setItem(newText);
			}else if(text.contains("\\)")){
				//String change = escape+")";
				String newText= text.replace("\\)",")");
				setItem(newText);
			}else if(text.contains("\\.")){
				//String change = escape+".";
				String newText= text.replace("\\.",".");
				setItem(newText);
			}else if(text.contains("\\!")){
				//String change = escape+"!";
				String newText= text.replace("\\!","!");
				setItem(newText);
			}
			else if(text.contains("\\_")){
				//String change = escape+"!";
				String newText= text.replace("\\_","_");
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

	public void convertUrl(String url){

		String converted="<a href=\""+url+"\">"+url+"</a>";
		setItem(converted);
	}

}