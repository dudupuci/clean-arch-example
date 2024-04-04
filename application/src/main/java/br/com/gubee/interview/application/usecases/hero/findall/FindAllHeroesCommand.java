package br.com.gubee.interview.application.usecases.hero.findall;

public record FindAllHeroesCommand(
        String name
) {
    public static FindAllHeroesCommand with(
            final String name
    ) {
        return new FindAllHeroesCommand(name);
    }
}
