package com.myintrest.phasect.service;

import com.myintrest.phasect.model.LocationStats;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
@Service
public class CoronoDataService {

         private List<LocationStats> allstats= new ArrayList<>();

    public List<LocationStats> getAllstats() {
        return allstats;
    }

    public String Url="https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";
        @PostConstruct
        @Scheduled(cron = "* 10 * * * *")
        public void fetchVirusData() throws IOException, InterruptedException {
             List<LocationStats> newStats= new ArrayList<>();
            HttpClient client = HttpClient.newHttpClient();
           HttpRequest request= HttpRequest.newBuilder()
                    .uri(URI.create(Url)).build();
          HttpResponse<String> httpResponse=client.send(request, HttpResponse.BodyHandlers.ofString());
          System.out.println(httpResponse.body());
            StringReader in;
            in = new StringReader(httpResponse.body());
            Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(in);

            for (CSVRecord record : records) {
                LocationStats locationStats = new LocationStats();
                locationStats.setState(record.get("Province/State"));
                locationStats.setCountry(record.get("Country/Region"));

                locationStats.setLatestTotalCases(Integer.parseInt(record.get(record.size() -1)));

//System.out.println(locationStats);
newStats.add(locationStats);


            }
            this.allstats=newStats;


        }
    }

