package ru.javarush.cryptocode;

public enum Types {
    ENCRYPTION {
        @Override
        public String getDescription() {
            return " - encryption document";
        }
    },
    DECRYPTION {
        @Override
        public String getDescription() {
            return " - decryption document";
        }
    },
    CRYPTO_BRUTE_FORCE {
        @Override
        public String getDescription() {
            return " - crypto analysis by brute force method";
        }
    },
    CRYPTO_STATISTIC_ANALYSIS {
        @Override
        public String getDescription() {
            return " - crypto analysis by statistic analysis";
        }
    };

    public String getDescription() {
        return null;
    }
}
