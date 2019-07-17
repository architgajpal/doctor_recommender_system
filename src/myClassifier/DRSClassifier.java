package myClassifier; 
import weka.core.*;
import weka.core.FastVector;
import weka.classifiers.meta.FilteredClassifier;
import java.util.List;
import java.util.ArrayList;
import java.io.*;

 public class DRSClassifier {
	 
	 double testWE,testORating;
	 int testQual;
	 String label;
	 
	 public DRSClassifier(double TWE,double TORating,int TQual){
		 this.testWE=TWE;
		 this.testORating=TORating;
		 this.testQual=TQual;
		 this.label="";
	 }

	String text;
	
	Instances instances;
	FilteredClassifier classifier;
		
	public void load(String fileName) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(fileName));
			String line;
			/*
			text = "";
			while ((line = reader.readLine()) != null) {
                text = text + " " + line;
            }
            */
			
			text = "";
			while ((line = reader.readLine()) != null) {
                text = text + " " + line;
                String[] temp = text.split(",");
                testWE = Double.parseDouble(temp[0]);
                testORating = Double.parseDouble(temp[1]);
                testQual = Integer.parseInt(temp[2]);
            }
			
//			System.out.println("===== Loaded text data: " + fileName + " =====");
			reader.close();
//			System.out.println(text);
		}
		catch (IOException e) {
			System.out.println("Problem found when reading: " + fileName);
		}
	}
			
	public void loadModel(String fileName) {
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName));
            Object tmp = in.readObject();
			classifier = (FilteredClassifier) tmp;
            in.close();
			System.out.println("===== Loaded model: " + fileName + " =====");
       } 
		catch (Exception e) {
			// Given the cast, a ClassNotFoundException must be caught along with the IOException
			System.out.println("Problem found when reading: " + fileName);
		}
	}
	
	public void makeInstance() {
		// Create the attributes, class and text
		FastVector fvNominalVal = new FastVector(3);
		fvNominalVal.addElement("HR");
		fvNominalVal.addElement("R");
		fvNominalVal.addElement("NR");
		Attribute attribute4 = new Attribute("class", fvNominalVal);
		Attribute attribute1 = new Attribute("wescore");
		Attribute attribute2 = new Attribute("overallrating");
		Attribute attribute3 = new Attribute("qualification");
		// Create list of instances with one element
		FastVector fvWekaAttributes = new FastVector(4);
		fvWekaAttributes.addElement(attribute1);
		fvWekaAttributes.addElement(attribute2);
		fvWekaAttributes.addElement(attribute3);
		fvWekaAttributes.addElement(attribute4);
		instances = new Instances("Test relation", fvWekaAttributes, 0);           
		// Set class index
		instances.setClassIndex(3);
		// Create and add the instance
		Instance instance = new Instance(4);
		instance.setValue(attribute1, testWE);
		instance.setValue(attribute2, testORating);
		instance.setValue(attribute3, testQual);
		
		// Another way to do it:
		// instance.setValue((Attribute)fvWekaAttributes.elementAt(1), text);
		instances.add(instance);
// 		System.out.println("===== Instance created with reference dataset =====");
		System.out.println(instances);
	}
	
	public void classify() {
		try {
			double pred = classifier.classifyInstance(instances.instance(0));
			System.out.println("===== Classified instance =====");
			System.out.println("Class predicted: " + instances.classAttribute().value((int) pred));
			this.label+=instances.classAttribute().value((int) pred);
		}
		catch (Exception e) {
			System.out.println("Problem found when classifying the text");
		}		
	}
	public String returnLabel(){
		return this.label;
	}
	
	/****original code including main function***/
//	public static void main (String[] args) {
//	
//		
//		DRSClassifier classifier;
//		classifier = new DRSClassifier();
//		classifier.load("src/DoctorTest.txt");
//		classifier.loadModel("C:\\Users\\Architgajpal\\workspace\\DoctorRecommenderSystem\\src\\DRSClassifier.dat");
//		classifier.makeInstance();
//		classifier.classify();
//	}
	
	/*******ends here*******/
	
	public void ClassifyFunc(){
		
//		classifier.load("src/DoctorTest.txt");
		this.loadModel("C:\\Users\\dell\\workspace\\DRSWeb\\src\\DRSClassifier.dat");
		this.makeInstance();
		this.classify();
		
	}
} 