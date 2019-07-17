package myClassifier;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Random;

import weka.classifiers.Evaluation;
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.meta.FilteredClassifier;
import weka.classifiers.trees.J48;
import weka.core.Instances;
import weka.core.converters.ArffLoader.ArffReader;
//import weka.core.converters.ArffLoader.ArffReader
import weka.core.converters.ConverterUtils.*;
import weka.filters.unsupervised.attribute.StringToWordVector;


public class DRSLearner {


	Instances trainData;
	StringToWordVector filter;
	FilteredClassifier classifier;
		
	public void loadDataset(String fileName) throws Exception {
		try {
			//BufferedReader reader = new BufferedReader(new FileReader(fileName));
			//ArffReader arff = new ArffReader(reader,1000);
			DataSource source = new DataSource(fileName);
			trainData = source.getDataSet();
			//trainData = arff.getData();
//			System.out.println("===== Loaded dataset: " + fileName + " =====");
			//reader.close();
		}
		catch (IOException e) {
			System.out.println("Problem found when reading: " + fileName);
		}
	}
	
	public void evaluate() {
		try {
			trainData.setClassIndex(3);
			filter = new StringToWordVector();
			filter.setAttributeIndices("last");
			classifier = new FilteredClassifier();
			classifier.setFilter(filter);
//			classifier.setClassifier(new NaiveBayes());
			classifier.setClassifier(new J48());
			Evaluation eval = new Evaluation(trainData);
			eval.crossValidateModel(classifier, trainData, 4, new Random(1));
			System.out.println(eval.toSummaryString());
			System.out.println(eval.toClassDetailsString());
//			System.out.println("===== Evaluating on filtered (training) dataset done =====");
		}
		catch (Exception e) {
			System.out.println("Problem found when evaluating");
		}
	}
	
	public void learn() {
		try {
			trainData.setClassIndex(3);
			filter = new StringToWordVector();
			filter.setAttributeIndices("last");
			classifier = new FilteredClassifier();
			classifier.setFilter(filter);
//			classifier.setClassifier(new NaiveBayes());
			classifier.setClassifier(new J48());
			classifier.buildClassifier(trainData);
			// Uncomment to see the classifier
			// System.out.println(classifier);
			System.out.println("===== Training on filtered (training) dataset done =====");
		}
		catch (Exception e) {
			System.out.println("Problem found when training");
		}
	}
	
	public void saveModel(String fileName) {
		try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName));
            out.writeObject(classifier);
            out.close();
 			System.out.println("===== Saved model: " + fileName + " =====");
        } 
		catch (IOException e) {
			System.out.println("Problem found when writing: " + fileName);
		}
	}
	
	public static void main (String[] args) throws Exception {
	
		DRSLearner learner;
		
		learner = new DRSLearner();
		//learner.loadDataset("C:\\Users\\Architgajpal\\workspace\\TestWeka\\src\\myFilteredClassifier\\smsspam.small.arff");
		learner.loadDataset("src/DoctorDataset.arff");
		// Evaluation must be done before training
		// More info in: http://weka.wikispaces.com/Use+WEKA+in+your+Java+code
		learner.evaluate();
		learner.learn();
		learner.saveModel("C:\\Users\\dell\\workspace\\DRSWeb\\src\\DRSClassifier.dat");	
	}
}