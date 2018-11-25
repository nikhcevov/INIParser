package com.github.ovchingus;

import java.io.*;

public class IniParser {
    IniParser() {
    }

    public void parse(InputStream input, IniHandler handler) throws IOException {
        this.parse((new InputStreamReader(input)), handler);
    }

    void parse(Reader input, IniHandler handler) throws IOException {
        LineNumberReader reader = new LineNumberReader(input);
        handler.startIni();
        String sectionName = null;

        for (String line = reader.readLine(); line != null; line = reader.readLine()) {
            line = line.trim();
            // пропуск пустых строк и комментариев
            if (line.length() != 0 && ";#".indexOf(line.charAt(0)) < 0) {
                // имя секции устанавливаем
                if (line.charAt(0) == '[') {
                    if (sectionName != null) {
                        handler.endSection();
                    }

                    if (line.charAt(line.length() - 1) != ']') {
                        this.parseError(line, reader.getLineNumber());
                    } else {
                        sectionName = this.unescape(line.substring(1, line.length() - 1).trim());
                        if (sectionName.length() == 0) {
                            this.parseError(line, reader.getLineNumber());
                        } else {
                            handler.startSection(sectionName);
                        }
                    }
                } else if (sectionName == null) {
                    this.parseError(line, reader.getLineNumber());
                } else {
                    // юникод 61 это "="
                    int idx = line.indexOf(61);
                    if (idx <= 0) {
                        this.parseError(line, reader.getLineNumber());
                    } else {
                        String name = this.unescape(line.substring(0, idx).trim());
                        String value = this.unescape(line.substring(idx + 1).trim());
                        if (name.length() == 0) {
                            this.parseError(line, reader.getLineNumber());
                        } else if (value.length() == 0) {
                            this.parseError(line, reader.getLineNumber());
                        } else {
                            handler.handleOption(name, value);
                        }
                    }
                }
            }
        }
        if (sectionName != null) {
            handler.endSection();
        }
        handler.endIni();
    }

    private void parseError(String line, int lineNumber) throws InvalidIniFormatException {
        throw new InvalidIniFormatException("Parse error (at line: " + lineNumber + "): " + line);
    }

    private void unescapeError(String line) throws InvalidIniFormatException {
        throw new InvalidIniFormatException("Invalid parameter value (in line: \"" + line + "\")");
    }

    private String unescape(String line) throws IOException {
        int n = line.length();
        StringBuilder buffer = new StringBuilder(n);

        char c;
        for (int i = 0; i < n; buffer.append(c)) {
            c = line.charAt(i++);
            // когда коммент прямо в строке
            if (c == ';') {
                return buffer.toString().trim(); //replaceAll(" ", "");
            }
            if (c == '\u0020') {
                while (c == '\u0020') {
                    c = line.charAt(i++);
                }
                if (c == ';')
                    return buffer.toString().trim();
                else unescapeError(line);
            }

            if (c == '\\') {
                c = line.charAt(i++);
                if (c == 'u') {
                    try {
                        int var10001 = i;
                        i += 4;
                        c = (char) Integer.parseInt(line.substring(var10001, i), 16);
                    } catch (RuntimeException var7) {
                        throw new IllegalArgumentException("Malformed \\uxxxx encoding.");
                    }
                } else {
                    int idx = "\\tnf".indexOf(c);
                    if (idx >= 0) {
                        c = "\\\t\n\f".charAt(idx);
                    }
                }
            }
        }
        return buffer.toString();
    }
}
