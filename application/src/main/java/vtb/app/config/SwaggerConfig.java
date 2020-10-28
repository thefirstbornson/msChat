package vtb.app.config;

//@Configuration
//@RequiredArgsConstructor
public class SwaggerConfig {
//    public static final String AUTHORIZATION_HEADER = "Authorization";
//
////    private final BuildProperties buildProperties;
//
//    @Bean
//    public Docket api() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .tags(new Tag("role-model", "Ролевая модель пользователей ИБ"),
//                        new Tag("dictionaries", "Системные справочники"))
//                .select()
//                .apis(RequestHandlerSelectors.basePackage("com.vtb"))
//                .paths(PathSelectors.any())
//                .build()
//                .securityContexts(Lists.newArrayList(securityContext()))
//                .securitySchemes(Lists.newArrayList(apiKey()))
//                .apiInfo(getApiInfo());
//    }
//
//    private ApiInfo getApiInfo() {
//        String version = String.join("_", buildProperties.getVersion(),
//                buildProperties.get("commit_hash"),
//                LocalDateTime.ofInstant(buildProperties.getTime(), ZoneId.systemDefault()).toString());
//
//        return new ApiInfo(
//                "VTB Admin Console",
//                "Административная консоль",
//                version,
//                null,
//                new Contact("Roman Fedorenko", null, "RFedorenko@innotechnum.com"),
//                null,
//                null,
//                Collections.emptyList()
//        );
//    }
//
//    private ApiKey apiKey() {
//        return new ApiKey("JWT", AUTHORIZATION_HEADER, "header");
//    }
//
//    private SecurityContext securityContext() {
//        return SecurityContext.builder()
//                .securityReferences(defaultAuth())
//                .build();
//    }
//
//    List<SecurityReference> defaultAuth() {
//        AuthorizationScope authorizationScope
//                = new AuthorizationScope("global", "accessEverything");
//        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
//        authorizationScopes[0] = authorizationScope;
//        return Lists.newArrayList(
//                new SecurityReference("JWT", authorizationScopes));
//    }

}

