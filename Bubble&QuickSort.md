##冒泡排序和快速排序
> 理解算法的核心思想才是最重要的。

### 冒泡排序
``` java
 public class bubbleSort {  
	public  bubbleSort(){  
		int a[]={49,38,65,97,76,13,27,49,78,34,12,64,5,4,62,99,98,54,56,17,18,23,34,15,35,25,53,51};  
		int temp=0;  
		for(int i=0;i<a.length-1;i++){  
			for(int j=0;j<a.length-1-i;j++){  
				if(a[j]>a[j+1]){  
					temp=a[j];  
					a[j]=a[j+1];  
					a[j+1]=temp;  
				}  
			}  
    }  
    for(int i=0;i<a.length;i++)  
        System.out.println(a[i]);     
}  
}  

```
### 快速排序
``` java
 public class quickSort {  
  
  inta[]={49,38,65,97,76,13,27,49,78,34,12,64,5,4,62,99,98,54,56,17,18,23,34,15,35,25,53,51};  
  
public quickSort(){  
  
    quick(a);  
  
    for(int i=0;i<a.length;i++)  
  
       System.out.println(a[i]);  
  
}  
  
publicint getMiddle(int[] list, int low, int high) {     
  
            int tmp = list[low];    //数组的第一个作为中轴     
  
            while (low < high) {     
  
                while (low < high && list[high] >= tmp) {     
  
                    high--;     
  
                }     
  
                list[low] = list[high];   //比中轴小的记录移到低端     
  
                while (low < high && list[low] <= tmp) {     
  
                    low++;     
  
                }     
  
                list[high] = list[low];   //比中轴大的记录移到高端     
  
            }     
  
           list[low] = tmp;              //中轴记录到尾     
  
            return low;                   //返回中轴的位置     
  
        }    
  
publicvoid _quickSort(int[] list, int low, int high) {      
  
            if (low < high) {     
  
               int middle = getMiddle(list, low, high);  //将list数组进行一分为二     
  
                _quickSort(list, low, middle - 1);        //对低字表进行递归排序     
  
               _quickSort(list, middle + 1, high);       //对高字表进行递归排序     
  
            }     
  
        }   
  
publicvoid quick(int[] a2) {     
  
            if (a2.length > 0) {    //查看数组是否为空     
  
                _quickSort(a2, 0, a2.length - 1);     
  
        }     
  
       }   
  
}  
```