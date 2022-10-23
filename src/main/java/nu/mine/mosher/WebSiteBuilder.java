package nu.mine.mosher;

import org.apache.commons.csv.CSVFormat;

import java.io.*;

public class WebSiteBuilder {
    public static void main(final String... args) throws IOException {
        final var fmt = CSVFormat.Builder.create()
            .setHeader()
            .setSkipHeaderRecord(true)
            .setTrim(true)
        .build();

        try (final var out = new PrintWriter(new BufferedOutputStream(new FileOutputStream(FileDescriptor.out)))) {
            out.println("<!doctype html>");
            out.println("<html>");
            out.println("<body>");
            out.println("<table>");

            try (
                final var in = new FileReader("data/content.csv");
                final var rr = fmt.parse(in))
            {
                out.println("<thead>");
                out.print("<tr>");
                for (final var h : rr.getHeaderNames()) {
                    out.print("<th>");
                    out.print(h);
                    out.print("</th>");
                }
                out.println("</tr>");
                out.println("</thead>");

                out.println("<tbody>");
                for (final var r : rr) {
                    out.print("<tr>");
                    for (final var f : r) {
                        out.print("<td>");
                        out.print(f);
                        out.print("</td>");
                    }
                    out.println("</tr>");
                }
                out.println("</tbody>");
            }


            out.println("</table>");
            out.println("</body>");
            out.println("</html>");
            out.flush();
        }

        System.err.flush();
        System.out.flush();
    }
}
