package br.com.gubee.interview.application.usecases.hero.findbyid;

public record FindHeroByIdCommand(
        String id
) {
    public static FindHeroByIdCommand with(
            final String id
    ) {
        return new FindHeroByIdCommand(id);
    }
}
