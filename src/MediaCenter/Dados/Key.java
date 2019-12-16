package MediaCenter.Dados;

public class Key {
        private Integer id;
        private String email;


        public Integer getId() {
            return this.id;
        }

        public String getEmail() {
            return this.email;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        @Override
        public boolean equals(Object obj) {
            if(obj != null && obj instanceof Key) {
                Key k = (Key) obj;
                return id.equals(k.id) && email.equals(k.email);
            }
            return false;
        }

        @Override
        public int hashCode() {
            return (id.toString() + email).hashCode();
        }
}

