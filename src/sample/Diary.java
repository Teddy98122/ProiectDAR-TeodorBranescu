package sample;

public class Diary {

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String content;

    public Diary( String content)
    {

        this.content=content;
    }
}
