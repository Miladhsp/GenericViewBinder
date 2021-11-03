package ir.mich.genericviewbinder;

class Protected {
    private final Reserve reserve;
    private final String key;

    protected Protected(String key, Reserve reserve) {
        this.key = key;
        this.reserve = reserve;
    }

    protected static Key invoke(Protected aProtected) {
        return new Key(aProtected);
    }

    protected interface Reserve {
        void queue();
    }

    protected static class Key {

        private final Protected aProtected;

        protected Key(Protected aProtected) {
            this.aProtected = aProtected;
        }

        protected void key(String key) {
            if (aProtected != null) {
                if (aProtected.key.equals(key)) {
                    aProtected.reserve.queue();
                }
            }
        }
    }
}

