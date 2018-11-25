package com.github.ovchingus;




import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


@SuppressWarnings("ALL")
class IniTest {

    // Возможности которые будут протестированы:
    // 1) метод load() с InputStream и Reader, так же с конструктором
    // 2) метод get() для секции, параметра и значения.

    @Test
    void iniLoadWithReader() throws IOException {
        Reader file1 = new FileReader("src/test/java/lab3/file1.ini");
        Ini ini = new Ini(file1);
        ini.load(file1);
    }

    @Test
    void iniLoadWithIS() throws IOException {
        InputStream file1 = new FileInputStream("src/test/java/lab3/file1.ini");
        Ini ini = new Ini(file1);
        ini.load(file1);
    }

    @Test
    void iniLoadWithReader_InvalidIniFormatExeptionWithValue() throws IOException {
        InvalidIniFormatException exception = assertThrows(InvalidIniFormatException.class, () -> {
            Reader file1 = new FileReader("src/test/java/lab3/file2.ini");
            Ini ini = new Ini(file1);
            ini.load(file1);
        });
        assertEquals("Parse error (at line: 2): StatisterTimeMs =", exception.getMessage());
    }

    @Test
    void iniLoadWithIO_InvalidIniFormatExeptionWithValue() throws IOException {
        InvalidIniFormatException exception = assertThrows(InvalidIniFormatException.class, () -> {
            InputStream file1 = new FileInputStream("src/test/java/lab3/file2.ini");
            Ini ini = new Ini(file1);
            ini.load(file1);
        });
        assertEquals("Parse error (at line: 2): StatisterTimeMs =", exception.getMessage());
    }

    @Test
    void iniLoadWithReader_InvalidIniFormatExeptionWithParameter() throws IOException {
        InvalidIniFormatException exception = assertThrows(InvalidIniFormatException.class, () -> {
            Reader file1 = new FileReader("src/test/java/lab3/file3.ini");
            Ini ini = new Ini(file1);
            ini.load(file1);
        });
        assertEquals("Parse error (at line: 3): = 1; Logging ncmd proto", exception.getMessage());
    }

    @Test
    void iniLoadWithIO_InvalidIniFormatExeptionWithParameter() throws IOException {
        InvalidIniFormatException exception = assertThrows(InvalidIniFormatException.class, () -> {
            InputStream file1 = new FileInputStream("src/test/java/lab3/file3.ini");
            Ini ini = new Ini(file1);
            ini.load(file1);
        });
        assertEquals("Parse error (at line: 3): = 1; Logging ncmd proto", exception.getMessage());
    }

    @Test
    void iniLoadWithReader_InvalidIniFormatExeptionWithSection() throws IOException {
        InvalidIniFormatException exception = assertThrows(InvalidIniFormatException.class, () -> {
            Reader file1 = new FileReader("src/test/java/lab3/file4.ini");
            Ini ini = new Ini(file1);
            ini.load(file1);
        });
        assertEquals("Parse error (at line: 8): [ADC_", exception.getMessage());
    }

    @Test
    void iniLoadWithIO_InvalidIniFormatExeptionWithSection() throws IOException {
        InvalidIniFormatException exception = assertThrows(InvalidIniFormatException.class, () -> {
            InputStream file1 = new FileInputStream("src/test/java/lab3/file4.ini");
            Ini ini = new Ini(file1);
            ini.load(file1);
        });

        assertEquals("Parse error (at line: 8): [ADC_", exception.getMessage());
    }

    @Test
    void iniLoadWithReader_InvalidIniFormatExeptionWithValueSpace() throws IOException {
        InvalidIniFormatException exception = assertThrows(InvalidIniFormatException.class, () -> {
            Reader file1 = new FileReader("src/test/java/lab3/file5.ini");
            Ini ini = new Ini(file1);
            ini.load(file1);
        });
        assertEquals("Invalid parameter value (in line: \"1 1; Logging ncmd proto\")", exception.getMessage());
    }

    @Test
    void iniLoadWithIO_InvalidIniFormatExeptionWithValueSpace() throws IOException {
        InvalidIniFormatException exception = assertThrows(InvalidIniFormatException.class, () -> {
            InputStream file1 = new FileInputStream("src/test/java/lab3/file5.ini");
            Ini ini = new Ini(file1);
            ini.load(file1);
        });
        assertEquals("Invalid parameter value (in line: \"1 1; Logging ncmd proto\")", exception.getMessage());
    }

    @Test
    void iniLoadWithReader_InvalidIniFormatExeptionWithSectionSpace() throws IOException {
        InvalidIniFormatException exception = assertThrows(InvalidIniFormatException.class, () -> {
            Reader file1 = new FileReader("src/test/java/lab3/file6.ini");
            Ini ini = new Ini(file1);
            ini.load(file1);
        });
        assertEquals("Invalid parameter value (in line: \"COMM     ON\")", exception.getMessage());
    }

    @Test
    void iniLoadWithIO_InvalidIniFormatExeptionWithSectionSpace() throws IOException {
        InvalidIniFormatException exception = assertThrows(InvalidIniFormatException.class, () -> {
            InputStream file1 = new FileInputStream("src/test/java/lab3/file6.ini");
            Ini ini = new Ini(file1);
            ini.load(file1);
        });
        assertEquals("Invalid parameter value (in line: \"COMM     ON\")", exception.getMessage());
    }

    @Test
    void iniLoadWithReader_InvalidIniFormatExeptionWithSectionName() throws IOException {
        InvalidIniFormatException exception = assertThrows(InvalidIniFormatException.class, () -> {
            Reader file1 = new FileReader("src/test/java/lab3/file7.ini");
            Ini ini = new Ini(file1);
            ini.load(file1);
        });
        assertEquals("Parse error (at line: 1): COMMON", exception.getMessage());
    }

    @Test
    void iniLoadWithIO_InvalidIniFormatExeptionWithSectionName() throws IOException {
        InvalidIniFormatException exception = assertThrows(InvalidIniFormatException.class, () -> {
            InputStream file1 = new FileInputStream("src/test/java/lab3/file7.ini");
            Ini ini = new Ini(file1);
            ini.load(file1);
        });
        assertEquals("Parse error (at line: 1): COMMON", exception.getMessage());
    }

    @Test
    void iniLoadWithReader_SectionTwice() throws IOException {
        Reader file1 = new FileReader("src/test/java/lab3/file8.ini");
        Ini ini = new Ini(file1);
        ini.load(file1);
    }

    @Test
    void iniLoadWithIO_SectionTwice() throws IOException {
        InputStream file1 = new FileInputStream("src/test/java/lab3/file8.ini");
        Ini ini = new Ini(file1);
        ini.load(file1);
    }

    @Test
    void iniLoadWithReader_Constructor() throws IOException {
        Ini ini = new Ini();
        ini.load(new FileReader("src/test/java/lab3/file1.ini"));
    }

    @Test
    void iniLoadWithIO_Constructor() throws IOException {
        Ini ini = new Ini();
        ini.load(new FileInputStream("src/test/java/lab3/file1.ini"));
    }

    @Test
    void iniLoadWithReader_WrongSectionName() throws IOException {
        NullSectionException exception = assertThrows(NullSectionException.class, () -> {
            Ini ini = new Ini();
            ini.load(new FileReader("src/test/java/lab3/file1.ini"));
            ini.get("LOL").get("A");
        });
        assertEquals("Section \"LOL\" isn`t exist in .ini file.", exception.getMessage());
    }

    @Test
    void iniLoadWithIO_WrongSectionName() throws IOException {
        NullSectionException exception = assertThrows(NullSectionException.class, () -> {
            Ini ini = new Ini();
            ini.load(new FileInputStream("src/test/java/lab3/file1.ini"));
            ini.get("LOL").get("A");
        });
        assertEquals("Section \"LOL\" isn`t exist in .ini file.", exception.getMessage());
    }

    @Test
    void iniLoadWithReader_WrongParameterName() throws IOException {
        NullParameterException exception = assertThrows(NullParameterException.class, () -> {
            Ini ini = new Ini();
            ini.load(new FileReader("src/test/java/lab3/file1.ini"));
            ini.get("COMMON").get("A");
        });
        assertEquals("Parameter \"A\" isn`t exist in COMMON section.", exception.getMessage());
    }

    @Test
    void iniLoadWithIO_WrongParameterName() throws IOException {
        NullParameterException exception = assertThrows(NullParameterException.class, () -> {
            Ini ini = new Ini();
            ini.load(new FileInputStream("src/test/java/lab3/file1.ini"));
            ini.get("COMMON").get("A");
        });
        assertEquals("Parameter \"A\" isn`t exist in COMMON section.", exception.getMessage());
    }

    @Test
    void iniLoadWithReader_WrongValue() throws IOException {
        InvalidIniFormatException exception = assertThrows(InvalidIniFormatException.class, () -> {
            Ini ini = new Ini();
            ini.load(new FileReader("src/test/java/lab3/file9.ini"));
            ini.get("COMMON").get("NullValueParameter");
        });
        assertEquals("Parse error (at line: 7): NullValueParameter = ; Path for file cache", exception.getMessage());
    }

    @Test
    void iniLoadWithIO_WrongValue() throws IOException {
        InvalidIniFormatException exception = assertThrows(InvalidIniFormatException.class, () -> {
            Ini ini = new Ini();
            ini.load(new FileInputStream("src/test/java/lab3/file9.ini"));
            ini.get("COMMON").get("NullValueParameter");
        });
        assertEquals("Parse error (at line: 7): NullValueParameter = ; Path for file cache", exception.getMessage());
    }


}