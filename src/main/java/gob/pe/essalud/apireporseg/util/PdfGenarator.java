package gob.pe.essalud.apireporseg.util;

import org.springframework.stereotype.Service;

@Service
public class PdfGenarator {
/*
    private static final Logger logger = LoggerFactory.getLogger(PdfGenarator.class);

    @Autowired
    private TemplateEngine templateEngine;

    @Autowired
    private ApplicationContext context;

    @Autowired
    ServletContext servletContext;

    String urlBase = "http://localhost:8080";

    public ByteArrayOutputStream createPdf(final String templateName, final Map map, final HttpServletRequest request, final HttpServletResponse response)
            throws DocumentException {

        logger.debug("Generando informe pdf");

        Assert.notNull(templateName, "The templateName can not be null");

        IWebContext ctx = new  SpringWebContext(request, response, servletContext, LocaleContextHolder.getLocale(), map, context);

        String processedHtml = templateEngine.process(templateName, ctx);

        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        try {

            ITextRenderer renderer = new ITextRenderer();
            renderer.setDocumentFromString(processedHtml, urlBase);

            renderer.layout();
            renderer.createPDF(bos, false);
            renderer.finishPDF();
            logger.info("PDF created correctamente");

        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    logger.error("Error creando pdf", e);
                }
            }
        }
        return bos;
    }
*/}
