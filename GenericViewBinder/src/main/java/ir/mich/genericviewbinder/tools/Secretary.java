package ir.mich.genericviewbinder.tools;

public class Secretary {
    private final Reserve reserve;

    public Secretary(Reserve reserve) {
        this.reserve = reserve;
    }

    public static void invoke(Secretary aSecretary) {
        if (aSecretary != null) {
            if (aSecretary.reserve != null) {
                aSecretary.reserve.queue();
            }
        }
    }

    public interface Reserve {
        void queue();
    }

}

