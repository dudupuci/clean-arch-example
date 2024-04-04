package br.com.gubee.interview.infrastructure.factory;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;


public class CreateHeroIT {

   /* @Mock
    private HeroFacade facade;
    @Mock
    private HeroRepository repository;

    private 'Hero' hero;
    private CreateHeroApiRequest request;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Given("given a new hero")
    public void givenANewHero() {
        hero = TestUtils.generateHeroInstanceFullArgs();
    }

    @When("verify if facade and repository was called")
    public void whenVerifyFacadeAndRepositoryWasCalled() {
        verify(facade, times(1)).create(request.toCommand());
        verify(repository, times(1)).save(any(Hero.class));
    }

    @Then("generate output and assert if is not null")
    public void thenGenerateOutputAndAssertIfIsNotNull() {
        CreateHeroOutput output = facade.create(request.toCommand());
        Assertions.assertNotNull(output);
    }

    */
}