package org.iis2024.mayo2024;

public class PEGIServiceImpl implements PEGIService {
    @Override
    public boolean validateAge
            (PEGI_CODE code, int age) {
        boolean result = false ;
        switch
        (code) {
            case PEGI3:
                result = age >=
                        3;
                break;
            case PEGI7:
                result = age >=
                        7;
                break;
            case PEGI12:
                result = age >= 12;
                break;
            case PEGI16:
                result = age >= 16;
                break;
            case PEGI18:
                result = age >= 18;
                break;
        }
        return result;
    }
}
