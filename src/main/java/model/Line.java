package model;

import java.time.LocalDate;

public class Line {
    Service service;
    Question question;
    ResponseType responseType;
    LocalDate date;
    int time;

    public Line() { }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public ResponseType getResponseType() {
        return responseType;
    }

    public void setResponseType(ResponseType responseType) {
        this.responseType = responseType;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Line{" +
                "service=" + service +
                ", question=" + question +
                ", responseType=" + responseType +
                ", date=" + date +
                ", time=" + time +
                '}';
    }
}
