module TOSAD2 {
    requires transitive json.simple;
    requires java.sql;
    exports hu.tosad2;
    opens hu.tosad2 to json.simple;
}