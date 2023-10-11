package data.model;
import java.util.ArrayList;
import java.util.List;

    public class Diary {

        private int id;
        private String username;
        private String password;
        private List<Entry> entries = new ArrayList<>();
        private boolean isLocked;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public List<Entry> getEntries() {
            return entries;
        }

        public void setEntries(List<Entry> entries) {
            this.entries = entries;
        }

        public boolean isLocked() {
            return isLocked;
        }

        public void setLocked(boolean locked) {
            isLocked = locked;
        }

        @Override
        public String toString() {
            return "Diary{" +
                    "id=" + id +
                    ", username='" + username + '\'' +
                    ", password='" + password + '\'' +
                    ", entries=" + entries +
                    ", isLocked=" + isLocked +
                    '}';
        }

    }

