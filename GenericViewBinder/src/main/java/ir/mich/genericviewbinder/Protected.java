package ir.mich.genericviewbinder;

class Protected {
    private final Reserve reserve;
    private final String key;

    public Protected(String key, Reserve reserve) {
        this.key = key;
        this.reserve = reserve;
    }

    public static Key invoke(Protected aProtected) {
        return new Key(aProtected);
    }

    public interface Reserve {
        void queue();
    }

    static class Key {

        private final Protected aProtected;

        public Key(Protected aProtected) {
            this.aProtected = aProtected;
        }

        public void key(String key) {
            if (aProtected != null) {
                if (aProtected.key.equals(key)) {
                    aProtected.reserve.queue();
                }
            }
        }
    }
}

