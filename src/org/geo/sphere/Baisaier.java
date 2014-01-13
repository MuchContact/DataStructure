package org.geo.sphere;

/*白塞尔大地主题反算已知(B1，L1)(B2，L2)求亮点之间的大地线长。
 * 与软件解算结果在5mm的误差范围。
 * */
public class Baisaier {
	public void calDistance(int Datum,double B1,double L1,double B2,double L2){
		double e2,W1,W2,sinu1,sinu2,cosu1,cosu2,L,a1,a2,b1,b2,g2,g0,r,p,q,sino,coso,o,sinA0,x,t1,t2,A,B,C,y,S,A1,k2,e12 = 0,b = 0;
		//角度转换成弧度。
		B1=(Math.PI / 180) * B1;
		L1=(Math.PI / 180) * L1;
		B2=(Math.PI / 180) * B2;
		L2=(Math.PI / 180) * L2;
		final double PI=3.141592653;
		g0=0.0;
		e2=0.0;
		//Datum 投影基准面类型：北京54基准面为54，西安80基准面为80，WGS84基准面为84
        if (Datum == 84)  
        {  
            e2 = 0.0066943799013; //第一偏心率的平方
            b=6356752;// 短半轴
            e12=0.00673949674227;//第二偏心率的平方。
        }  
        else if (Datum == 54)  
        {  
            e2= 0.006693421622966; 
            b=6356863;
            e12=0.006738525414683;
        }  
        else if (Datum == 80)  
        {  
            e2 = 0.006694384999588;
            b=6356755;
            e12=0.006739501819473;
        } 
		 W1=Math.sqrt(1-e2*Math.sin(B1)*Math.sin(B1));
		 W2=Math.sqrt(1-e2*Math.sin(B2)*Math.sin(B2));
		 sinu1=Math.sin(B1)*Math.sqrt(1-e2)/W1;
		 sinu2=Math.sin(B2)*Math.sqrt(1-e2)/W2;
		 cosu1=Math.cos(B1)/W1;
		 cosu2=Math.cos(B2)/W2;
		 L=L2-L1;
		 a1=sinu1*sinu2;
		 a2=cosu1*cosu2;
		 b1=cosu1*sinu2;
		 b2=sinu1*cosu2;
		/*逐次趋近法求解A1*/
		int g=0;
		 r=L;
		while(true){
			 p=cosu2*Math.sin(r);
			 q=b1-b2*Math.cos(r);
			 A1=Math.atan(p/q);
			/*判断A1*/
			if(p>0 && q>0){
				A1=Math.abs(A1);
			}
			else if(p>0 && q<0){ 
				A1=PI-Math.abs(A1);
			}
			else if(p<0 && q<0){
				A1=PI+Math.abs(A1);
			} 
			else{
				A1=2*PI-Math.abs(A1);
			}
			 sino=p*Math.sin(A1)+q*Math.cos(A1);
			 coso=a1+a2*Math.cos(r);
			 o=Math.atan(sino/coso);
			/*判断o*/
			if(coso>0){
				o=Math.abs(o);
			}
				
			else{
				o=PI-Math.abs(o);
			}
		
			 sinA0=cosu1*Math.sin(A1);
			 x=2*a1-(1-sinA0*sinA0)*coso;
//			 t1=(33523299-(28189-70*(1-sinA0*sinA0))*(1-sinA0*sinA0))*1e-10;
//			 t2=(28189-94*(1-sinA0*sinA0))*1e-10;
			 t1=(e2/2+Math.pow(e2, 2)/8+Math.pow(e2, 3)/16)-(Math.pow(e2, 2)/16+Math.pow(e2, 3)/16)*(1-sinA0*sinA0)+(3*Math.pow(e2, 2)/128)*(1-sinA0*sinA0)*(1-sinA0*sinA0);
			 t2=2*((Math.pow(e2, 2)/32+Math.pow(e2, 3)/32)*(1-sinA0*sinA0)-(Math.pow(e2, 3)/64)*(1-sinA0*sinA0)*(1-sinA0*sinA0));
			 System.out.println(t1);
			 System.out.println(t2);
			 g2=(t1*o-t2*x*sino)*sinA0;
			if(Math.abs(g2-g0)<=1e-100){
				break;
			} 
			else{
				r=L+g2;
				g0=g2;
				g++;
			
			}
		}
		System.out.println(g);
		/*求解S*/
		k2=e12*(1-sinA0*sinA0);
//		A=6356863.020+(10708.949-13.474*(1-sinA0*sinA0))*(1-sinA0*sinA0);
//		B=10708.938-17.956*(1-sinA0*sinA0);
//		C=4.487;
		 A=b*(1+k2/4-3*Math.pow(k2, 2)/64+5*Math.pow(k2, 3)/256);
		 B=2*b*(k2/8-Math.pow(k2, 2)/32 + 15*Math.pow(k2, 3)/1024)/(1-sinA0*sinA0);
		 C=2*b*(Math.pow(k2, 2)/128-3*Math.pow(k2, 3)/512)/(1-sinA0*sinA0)/(1-sinA0*sinA0);
		System.out.println(A);
		System.out.println(B);
		System.out.println(C);
		 y=((1-sinA0*sinA0)*(1-sinA0*sinA0)-2*x*x)*coso;
		 S=A*o+(B*x+C*y)*sino;
		System.out.println(S);
		
		
	}
	
	public static void main(String[] args) {
		Baisaier bs=new Baisaier();
		//bs.change(84,47.781290833333, 35.8267583333, 48.069344, 36.245847361111);
		bs.calDistance(84,40.043244000000, 130.1, 40.7633063055556000, 130.20);
		
	}
}
	
	