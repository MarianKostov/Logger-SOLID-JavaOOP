package utilities;

public class LogFile implements File{
    private StringBuilder inMemoryContent;

    public LogFile() {
        this.inMemoryContent = new StringBuilder();
    }

    @Override
    public void write(String line) {
        this.inMemoryContent
                .append(line)
                .append(System.lineSeparator());
    }

    @Override
    public int size() {
        return this.inMemoryContent
                .chars()
                .filter(e -> {
            if ((e >= 'a' && e <= 'z') || (e >= 'A' && e <= 'Z')) {
                return true;
            }
            return false;
        }).sum();
    }
}
