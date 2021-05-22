package sample;

public class Diary {
    public String number;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String content;

    public Diary(String number, String content)
    {
        this.number=number;
        this.content=content;
    }
}
