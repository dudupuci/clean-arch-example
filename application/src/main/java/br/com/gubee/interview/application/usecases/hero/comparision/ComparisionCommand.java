package br.com.gubee.interview.application.usecases.hero.comparision;

public record ComparisionCommand(
        String heroId,
        String anotherHeroId
) {
}
