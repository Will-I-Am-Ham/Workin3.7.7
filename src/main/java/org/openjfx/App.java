package org.openjfx;

import javafx.application.Application; 
import static javafx.application.Application.launch;

import java.util.ArrayList;
import java.util.Arrays;

import javafx.scene.Group; 
import javafx.scene.Scene; 
import javafx.stage.Stage; 
import javafx.scene.chart.NumberAxis; 
import javafx.scene.chart.ScatterChart; 
import javafx.scene.chart.XYChart; 
         

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * JavaFX App
 */
public class App extends Application {
    
   ArrayList<Movie> movies = new ArrayList<Movie>();
   int csvlength = 6000;

    @Override
    public void start(Stage stage) throws Exception { 

      int numerrs = 0; 

        int n = 0;
        BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\meow1\\OneDrive\\Desktop\\hell\\hell2\\sample\\titles.csv"));  
        String line = "";  
        br.readLine();
        n++;

        /*String id,
         String title,
         String description,
         int releaseYear, 
         String ageRating,
         int runtime, 
         ArrayList<String> genres, 
         double imdbScore, 
         double tmdbScore */
        try{
            while ((line = br.readLine()) != null)   //returns a Boolean value  
            {
                String[] ceral = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
                try{
                movies.add(new Movie(
                    ceral[0],
                    ceral[1],
                    ceral[3],
                    Integer.parseInt(ceral[4]),
                    ceral[5],
                    Integer.parseInt(ceral[6]),
                    new ArrayList<String>(Arrays.asList(ceral[7].replace("[","").replace("]","").split(","))),
                    Double.parseDouble(ceral[11]),
                    Double.parseDouble(ceral[14])
                    ));
               //movies.get(n-1).print();
               //System.out.println(n);
                n++;
                }
                catch(Exception e){ //I think most of the errors that it thorws out is due to the split funcion spltiing commas within the coloumns :( shouldent matter though only 3 in 100 lines seem to have an error
                  System.out.println("Error: at line " + n + " in CSV somthing wrong with csv or csv reader!");
                  numerrs++;
                }
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }

        movies.get(0).print();

      System.out.println("percent errors / not included: " + (double)numerrs/(double)csvlength * 100 + "%");

        
        //Defining the x axis               
        NumberAxis xAxis = new NumberAxis(0, 12, 3); 
        xAxis.setLabel("Area");          
                
        //Defining the y axis 
        NumberAxis yAxis = new NumberAxis(0, 16, 4); 
        yAxis.setLabel("Weight");

        //Creating the Scatter chart 
        ScatterChart<String, Number> scatterChart = 
        new ScatterChart(xAxis, yAxis);         
            
        //Prepare XYChart.Series objects by setting data 
        XYChart.Series series = new XYChart.Series();  
        series.getData().add(new XYChart.Data(8, 12)); 
        series.getData().add(new XYChart.Data(4, 5.5)); 
        series.getData().add(new XYChart.Data(11, 14)); 
        series.getData().add(new XYChart.Data(4, 5)); 
        series.getData().add(new XYChart.Data(3, 3.5)); 
        series.getData().add(new XYChart.Data(6.5, 7));  
                    
        //Setting the data to scatter chart        
        scatterChart.getData().addAll(series); 
            
        //Creating a Group object  
        Group root = new Group(scatterChart); 
            
        //Creating a scene object 
        Scene scene = new Scene(root, 600, 400);  
        
        //Setting title to the Stage 
        stage.setTitle("Scatter Chart"); 
            
        //Adding scene to the stage 
        stage.setScene(scene); 
            
        //Displaying the contents of the stage 
        stage.show(); 
    } 

    public static void main(String[] args) {
        launch();
    }
}


class Movie {
    private String id;
    private String title;
    private String description;
    private int releaseYear;
    private String ageRating;
    private int runtime;
    private ArrayList<String> genres;
    private double imdbScore;
    private double tmdbScore;
    private double averageScore;
 
    public Movie(String id, String title, String description, int releaseYear, String ageRating, int runtime, ArrayList<String> genres, double imdbScore, double tmdbScore) {
       this.id = id;
       this.title = title;
       this.description = description;
       this.releaseYear = releaseYear;
       this.ageRating = ageRating;
       this.runtime = runtime;
       this.genres = genres;
       this.imdbScore = imdbScore;
       this.tmdbScore = tmdbScore;
       this.averageScore = Math.floor((imdbScore + tmdbScore) / 2.0 * 100.0) / 100.0;
    }
 
    public String getTitle() {
       return this.title;
    }
 
    public String getDescription() {
       return this.description;
    }
 
    public int getReleaseYear() {
       return this.releaseYear;
    }
 
    public String getAgeRating() {
       return this.ageRating;
    }
 
    public int getRuntime() {
       return this.runtime;
    }
 
    public ArrayList<String> getGenres() {
       return this.genres;
    }
 
    public double getImdbScore() {
       return this.imdbScore;
    }
 
    public double getTmdbScore() {
       return this.tmdbScore;
    }
 
    public double getAverageScore() {
       return this.averageScore;
    }
 
    public void print() {
       System.out.println("ID: " + this.id + " Title: " + this.title + " Description: " + this.description + " Release Year: " + this.releaseYear + " Age Rating: " + this.ageRating + " Runtime: " + this.runtime + " Genres: " + this.genres + "rating: " + this.imdbScore);
    }
 
    public static void main(String[] args) {
    }
 }