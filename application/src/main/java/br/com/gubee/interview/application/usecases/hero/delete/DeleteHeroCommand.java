package br.com.gubee.interview.application.usecases.hero.delete;

public record DeleteHeroCommand(
        String id
) {
    public static DeleteHeroCommand DeleteHeroCommand(
            final String id
    ) {
        return new DeleteHeroCommand(id);
    }
}
