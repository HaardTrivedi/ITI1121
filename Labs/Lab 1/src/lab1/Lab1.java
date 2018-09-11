package lab1;

import java.util.Arrays;
import java.util.Scanner;

public class Lab1{
    public static void FizzBuzz(){
        String f = "Fizz";
        String b = "Buzz";
        for (int i=1;i<=30;i++){
            if (i%15==0){
                System.out.println(f+b);
            }
            else if(i%3==0){
                System.out.println(f);
            }
            else if (i%5==0){
                System.out.println(b);
            }
        }
    }
    
    public static void createArray(int elements) {
        int array[] = new int[elements];
        for (int i=0;i<array.length; i++){
            int sq = i*i; array[i] = sq;
            System.out.println("The square of " + i + " is: " + array[i]);
        }
    }
    
    public static double calculateAverage(double[] values){
        double result, sum=0;
        for (int i=0;i<values.length;i++){
            sum = sum + values[i];
        }
        result = sum/values.length;
        return result;
    }
    
    public static int[] Insertion(int[] array, int index, int number){
        int[] newarray = new int[(array.length + 1)];
        for (int i=0; i<newarray.length;i++){
            if (i < index){
                newarray[i]=array[i];
            }else if (i==index){
                newarray[i]=number;
            }else if (i>index){
                newarray[i]=array[i-1];
            }            
        }
        return newarray;
    }
    
    public static void reverseSort(char[] values){
        Arrays.sort(values);
        char[] val = new char[values.length];
        for (int i=0;i<values.length; i++){
            val[i]= values[i];
        }
        int j=val.length - 1;
        for (int i=0;i<values.length; i++){
            values[i]= val[j];
            j--;
        }
    }
    
    public static void calculateAverage2(double[] notes){
            int sum=0;
            for (int i =0;i<notes.length;i++){
                sum += notes[i]; 
            }
            double average = sum / notes.length;
            System.out.println(average);
	}
	
	public static void calculateMedian(double[] notes){
            double median;
            if (notes.length%2==0){
                median = notes[notes.length/2]+notes[(notes.length/2)-1];
            }else {
                median = notes[notes.length/2];
            }
            System.out.println(median);
	}
	
	public static void calculateNumberFailed(double[] notes){
            int numfail=0;
            for (int i =0;i<notes.length;i++){
                if (notes[i]<50){
                    numfail+=1;
                }
            }
            System.out.println(numfail);
	}
	
	public static void calculateNumberPassed(double[] notes){
            int numpass=0;
            for (int i =0;i<notes.length;i++){
                if (notes[i]>=50){
                    numpass+=1;
                }
            }
            System.out.println(numpass);
	}

    
    public static void main(String[] args){
        FizzBuzz();
        createArray(13);
        
        double[] valuesArray = new double[]{100.0,34.0,72.0,56.0,82.0,67.0,94.0};
        System.out.println("The average is: " + calculateAverage(valuesArray));
        
        int[] array = new int[]{1,5,4,7,9,6};
        System.out.println("Array before insertion:");
        for (int i = 0; i<array.length;i++){
            System.out.println(array[i]);
        }
        int[] newa = Insertion(array, 3, 15);
        System.out.println("Array after insertion of " + 15 + " at position 3:");
        for (int i = 0; i<newa.length;i++){
            System.out.println(newa[i]);
        }
        
        char[] unorderedLetters = new char[]{'b', 'm', 'z', 'a', 'u'};
        reverseSort(unorderedLetters); 
        for (int i = 0 ; i < unorderedLetters.length; i++ ){
            System.out.print(unorderedLetters[i]);
        }
        
        Scanner sc = new Scanner(System.in);
        double[] marks = new double[10];
        double mark;
        for (int i =0;i<10;i++){
            System.out.println("Enter a student's mark:");
            mark = sc.nextDouble();
            marks[i] = mark;
        }
        Arrays.sort(marks);
        calculateAverage2(marks);
        calculateMedian(marks);
        calculateNumberFailed(marks);
        calculateNumberPassed(marks);
    }
}
