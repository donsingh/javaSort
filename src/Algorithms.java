/**
 * This is where the Sorting Happens.
 * The class implements Runnable as to be 
 * able to use Thread.sleep() for putting a
 * delay into the sorting to be able to render
 * and recolor the changes.
 * @author Don Bhrayan M. Singh
 */
class Algorithms implements Runnable {

  private Double[] data;
  private int delay;
  private DataBars panel;
  private int type;
  private int size;
  public Double[] helper;
 /**
  * This constructor is to set the private fields needed
  * for the sorting
  * @param data array of double elements that is to be sorted
  * @param panel the object panel where the bars are rendered.
  * @param delay passes the specified delay time in the settings menue
  * @param type contains the index of the jComboBox for selected sorting algo
  */
  Algorithms(Double[] data, DataBars panel, int delay, int type) {
    this.data = data;
    this.panel = panel;
    this.delay = delay;
    this.type = type;
    this.size = data.length;
    this.helper = new Double[data.length];
    
  }
  
  /**
   * Begins the sorting process. Starts a timer object and 
   * selects the proper sorting method based on the param type
   */
  public void run() {
    TimerClass timez = new TimerClass();
    timez.TimerStart();
    switch(type){
        case 0: bubbleSort();
            break;
        case 1: selectionSort();
            break;
        case 2: MergeSort(data, 0, (data.length-1));
            break;
        case 3: HeapSort();
            break;
        case 4: ShellSort(data, data);
            break;
        case 5: QuickSort(data,0, size-1);
            break;
        case 6: InsertionSort(data);
            break;
        case 7: CombSort(data);
            break;
        case 8: ExchangeSort(data);
    }
    timez.done();
  }

  public void bubbleSort()
  {
      boolean swapped = true;
      double j = 0;
      double tmp;
      while (swapped) {
            swapped = false;
            j++;
            for (int i = 0; i < data.length - j; i++) {
                  panel.setValues(data, data[i], data[i + 1]);
                  try {
                            Thread.sleep(delay);
                        } catch (Exception exception) {
                            System.out.println(exception);
                        }
                  if (data[i] > data[i + 1]) {
                        tmp = data[i];
                        data[i] = data[i + 1];
                        data[i + 1] = tmp;
                        swapped = true;
                        panel.setValues(data, data[i], data[i + 1]);
                  }
            }
    }
    panel.setValues(data, null, null);
  }

  public void selectionSort()
  {
     int i;
     int j;
     int first;
     double temp;
     for (i = data.length - 1; i > 0; i --)
     {

          try {Thread.sleep(delay);} catch (Exception exception) { System.out.println(exception); }panel.setValues(data, data[i], data[i]);
          first = 0;   //initialize to subscript of first element
          for(j = 1; j <= i; j ++)   //locate smallest element between positions 1 and i.
          {
               if(data[first] < data[j])
                 first = j;
               try {Thread.sleep(delay);} catch (Exception exception) { System.out.println(exception); }panel.setValues(data, data[first], data[j]);
          }
          temp = data[first];   //swap smallest found with element in position i.
          data[first] = data[i];
          data[i] = temp;
          try {
                Thread.sleep(delay);
            } catch (Exception exception) {
                System.out.println(exception);
          }panel.setValues(data, data[i], data[first]);
      }

  }

  public void MergeSort(Double[] array, int low, int high) {

        // check if low is smaller then high, if not then the array is sorted
        if (low < high) {
          // Get the index of the element which is in the middle
          int middle = low + (high - low) / 2;

          try {
                 Thread.sleep(delay);
          } catch (Exception exception) {
                 System.out.println(exception);
              }panel.setValues(array, array[low], array[middle],array[high]);

          // Sort the left side of the array
          MergeSort(array, low, middle);
          try {
                 Thread.sleep(delay);
          } catch (Exception exception) {
                 System.out.println(exception);
              }panel.setValues(array, array[low], array[middle],array[high]);
          // Sort the right side of the array
          MergeSort(array, middle + 1, high);
          // Combine them both
          try{Thread.sleep(delay);} catch (Exception exception) {System.out.println(exception);}
          panel.setValues(array, array[low], array[middle],array[high]);
          merge(low, middle, high);
          try{Thread.sleep(delay);} catch (Exception exception) {System.out.println(exception);}
          panel.setValues(array, null, null,array[high]);
        }

    }


  public void merge(int low, int middle, int high) {

      // Copy both parts into the helper array
      for (int i = low; i <= high; i++) {
        helper[i] = data[i];
      }

      int i = low;
      int j = middle + 1;
      int k = low;
      // Copy the smallest data from either the left or the right side back
      // to the original array
      while (i <= middle && j <= high) {
        try{Thread.sleep(delay);} catch (Exception exception) {System.out.println(exception);}
        panel.setValues(data, data[k], null,data[j]);
        if (helper[i] <= helper[j]) {
          data[k] = helper[i];
          i++;
        } else {
          data[k] = helper[j];
          j++;
        }

        k++;
      }
      while (i <= middle) {
        try{Thread.sleep(delay);} catch (Exception exception) {System.out.println(exception);}
        panel.setValues(data, data[i], null,data[k]);
        data[k] = helper[i];
        k++;
        i++;
      }
       
    }

  public void HeapSort(){
        int N;
        N = heapify(data);
        for (int i = N; i > 0; i--)
        {
            swap(data,0, i);
            N = N-1;
            maxheap(data, 0, N);
        }
        try{Thread.sleep(delay);} catch (Exception exception) {System.out.println(exception);}
        panel.setValues(data, data[N], data[N]);
  }

  public int heapify(Double[] arr)
  {
      int N;
      N = arr.length-1;
      for (int i = N/2; i >= 0; i--)
          maxheap(arr, i, N);
      return N;
      
  }

  public void maxheap(Double[] arr, int i, int N)
  {
      int left = 2*i ;
      int right = 2*i + 1;
      int max = i;
      if (left <= N && arr[left] > arr[i])
          max = left;
      if (right <= N && arr[right] > arr[max])
          max = right;
      try{Thread.sleep(delay);} catch (Exception exception) {System.out.println(exception);}
        panel.setValues(arr, arr[max], arr[i]);
      if (max != i)
      {
          swap(arr, i, max);
          try{Thread.sleep(delay);} catch (Exception exception) {System.out.println(exception);}
          panel.setValues(arr, arr[i], arr[max], arr[N]);
          maxheap(arr, max, N);
      }
  }
  
  public void swap(Double[] arr, int i, int j)
    {
        Double tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp; 
    } 
  
  public void ShellSort( Comparable[] a, Double[] val)
  {
    for( int gap = a.length / 2; gap > 0;  gap = gap == 2 ? 1 : (int) ( gap / 2.2 ) )
        for( int i = gap; i < a.length; i++ )
        {
            Comparable tmp = a[ i ];
            int j = i;
            try{Thread.sleep(delay);} catch (Exception exception) {System.out.println(exception);}
            panel.setValues(val, val[i], val[gap]);
            for( ; j >= gap && tmp.compareTo( a[ j - gap ] ) < 0; j -= gap ){
                a[ j ] = a[ j - gap ];
                try{Thread.sleep(delay);} catch (Exception exception) {System.out.println(exception);}
                panel.setValues(val, val[i], val[j-gap], val[j]);
            }
            a[j] = tmp;
        }
    panel.setValues(data, null, null);
    
  }
  
   public void QuickSort(Double[] array, int lowerIndex, int higherIndex){
        int i = lowerIndex;
        int j = higherIndex;
        try{Thread.sleep(delay);} catch (Exception exception) {System.out.println(exception);}
        panel.setValues(array, array[i],array[j]);
        // calculate pivot number, I am taking pivot as middle index number
        Double pivot = array[lowerIndex+(higherIndex-lowerIndex)/2];
        // Divide into two arrays
        while (i <= j) {

            while (array[i] < pivot) {
                try{Thread.sleep(delay);} catch (Exception exception) {System.out.println(exception);}
                panel.setValues(array, array[i],array[j]);
                i++;
            }
            while (array[j] > pivot) {
                j--;
                try{Thread.sleep(delay);} catch (Exception exception) {System.out.println(exception);}
                panel.setValues(array, array[i],array[j]);
            }
            if (i <= j) {
                try{Thread.sleep(delay);} catch (Exception exception) {System.out.println(exception);}
                panel.setValues(array, array[i],array[j]);
                swap(array, i, j);
                try{Thread.sleep(delay);} catch (Exception exception) {System.out.println(exception);}
                panel.setValues(array, array[i],array[j]);
                i++;
                try{Thread.sleep(delay);} catch (Exception exception) {System.out.println(exception);}
                panel.setValues(array, array[i],array[j]);
                j--;
            }
        }
        // call quickSort() method recursively
        if (lowerIndex < j)
            QuickSort(array, lowerIndex, j);
        if (i < higherIndex)
            QuickSort(array, i, higherIndex);
        panel.setValues(data, null, null);
   }
   
   public void InsertionSort(Double[] val){
       try{Thread.sleep(delay);} catch (Exception exception) {System.out.println(exception);}
       panel.setValues(val,null,null);
       for(int i= 1; i < val.length; i++){
           Double temp = val[i];
           int j;
           try{Thread.sleep(delay);} catch (Exception exception) {System.out.println(exception);}
           panel.setValues(val,val[i],null, val[i]);
           for(j = i-1; j >= 0 && temp < val[j]; j--){
               try{Thread.sleep(delay);} catch (Exception exception) {System.out.println(exception);}
               panel.setValues(val,null, val[j], val[i]);
               val[j+1] = val[j];
               try{Thread.sleep(delay);} catch (Exception exception) {System.out.println(exception);}
               panel.setValues(val,null, val[j],val[i]);
           }
           val[j+1] = temp;  
       }
       panel.setValues(data, null, null);
   }
   
   public void CombSort(Double[] input){
        int gap = input.length;
        boolean swapped = true;
        try{Thread.sleep(delay);} catch (Exception exception) {System.out.println(exception);}
        panel.setValues(input, null,null);
        while (gap > 1 || swapped) {
            if (gap > 1) {
                gap = (int) (gap / 1.3);
                try{Thread.sleep(delay);} catch (Exception exception) {System.out.println(exception);}
                panel.setValues(input, null,null, input[gap]);
            }
        swapped = false;
        for (int i = 0; i + gap < input.length; i++) {
            try{Thread.sleep(delay);} catch (Exception exception) {System.out.println(exception);}
            panel.setValues(input, input[i],input[i+gap], input[gap]);
            if (input[i].compareTo(input[i + gap]) > 0) {
                Double t = input[i];
                input[i] = input[i + gap];
                input[i + gap] = t;
                swapped = true;
                try{Thread.sleep(delay);} catch (Exception exception) {System.out.println(exception);}
                panel.setValues(input, input[i],input[i+gap], input[gap]);
            }
        }
    }
    panel.setValues(data, null, null);
   }
   
   public void ExchangeSort(Double[] array){
      try{Thread.sleep(delay);} catch (Exception exception) {System.out.println(exception);}
      panel.setValues(array, null,null);
      Double temp; 
      for (int i=0; i < array.length-1; i++) {
         try{Thread.sleep(delay);} catch (Exception exception) {System.out.println(exception);}
         panel.setValues(array, array[i],null);
         for (int j = i+1; j<array.length; j++) {
            try{Thread.sleep(delay);} catch (Exception exception) {System.out.println(exception);}
            panel.setValues(array, array[i],array[j]);
            if(array[ i ] > array[ j ]) {
               try{Thread.sleep(delay);} catch (Exception exception) {System.out.println(exception);}
               panel.setValues(array, null,array[i],array[j]);
               temp = array[i]; 
               array[i] = array[j];
               array[j] = temp; 
            }           
         }
      panel.setValues(data, null, null);
        }
  }
}


