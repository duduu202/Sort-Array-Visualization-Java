package Algorithms;

import Principal.sArray;

public class Bead_sort {
	public Bead_sort(sArray a){
		beadSort(a);
		a.verificar();
	}
	void beadSort(sArray a)
	{
		int max=a.readElement(0);
		for(int i=1;i<a.getLength();i++)
			if(a.readElement(i)>max)
				max=a.readElement(i);
 
		//Set up abacus
		char[][] grid=new char[a.getLength()][max];
		int[] levelcount=new int[max];
		for(int i=0;i<max;i++)
		{
			levelcount[i]=0;
			for(int j=0;j<a.getLength();j++)
				grid[j][i]='_';
		}
		/*
		display1D(arr);
		display1D(levelcount);
		display2D(grid);
		*/
 
		//Drop the beads
		for(int i=0;i<a.getLength();i++)
		{
			int num=a.readElement(i);
			for(int j=0;num>0;j++)
			{
				grid[levelcount[j]++][j]='*';
				num--;
			}
		}
		//Count the beads
		int[] sorted=new int[a.getLength()];
		for(int i=0;i<a.getLength();i++)
		{
			int putt=0;
			for(int j=0;j<max&&grid[a.getLength()-1-i][j]=='*';j++)
				putt++;
			a.set(i, putt);
		}
 
	}
}
