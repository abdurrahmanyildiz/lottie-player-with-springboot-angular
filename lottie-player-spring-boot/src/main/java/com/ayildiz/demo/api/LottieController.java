package com.ayildiz.demo.api;

import com.ayildiz.demo.model.LottieFile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequestMapping("/api/lottie")
@RestController()
public class LottieController {

    List<LottieFile> files = new ArrayList<>();

    public LottieController(){
        files.add(new LottieFile(UUID.randomUUID(),"Vespa", "https://assets4.lottiefiles.com/packages/lf20_N1zUrn.json"));
        files.add(new LottieFile(UUID.randomUUID(),"Bird", "https://assets3.lottiefiles.com/packages/lf20_qFyykj.json"));
        files.add(new LottieFile(UUID.randomUUID(),"Bike", "https://assets3.lottiefiles.com/packages/lf20_OQlozm.json"));
        files.add(new LottieFile(UUID.randomUUID(),"Stay Home Stay Safe ", "https://assets6.lottiefiles.com/packages/lf20_buqccM.json"));
    }


    @GetMapping(path = "/{id}")
    public String getLottieJson(@PathVariable("id") UUID id) {

        if (id != null) {
            LottieFile lf = files.stream().filter(f -> f.getID().equals(id)).findFirst().get();

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(lf.getUrl())).build();

            HttpResponse<String> response;
            try {
                response = client.send(request, HttpResponse.BodyHandlers.ofString());
                return response.body();
            } catch (Exception e) {
                // do smt
            }
        }
        return null;
    }

    @GetMapping(path = "/all")
    public List<LottieFile> getLottieFiles() {
        return files;
    }

}
