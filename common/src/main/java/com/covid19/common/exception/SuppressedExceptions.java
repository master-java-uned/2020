package com.covid19.common.exception;

public class SuppressedExceptions {
    public static void main(String[] args) throws Exception {
        try {
            callTryFinallyBlock();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void callTryFinallyBlock() throws Exception {
        try
        {
            throw new TryException();
        }
        finally
        {
            FinallyException fEx = new FinallyException();
            throw fEx;
        }
    }
}

class TryException extends Exception {
}

class FinallyException extends Exception {
}
