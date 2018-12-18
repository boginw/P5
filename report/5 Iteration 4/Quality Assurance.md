# Quality Assurance

This section will explain how the groups approached quality assurance, and the considerations behind the decisions the group made. By quality assurance this report refers to the quality of the code, with primary focus on the correctness. 
More specifically if the code correctly does what the group intends it to do.    
<!--I dette afsnit vil der blive gennemgået hvordan gruppen håndterede kvalitetsikring og hvilke overvejelser der blev gjort i forbindelse med kvalitetsikringen. 
Med kvalitetsikring henvises der til kvalitet af koden, hovedsageligt med fokus på korrekthed. Altså hvorvidt koden udformet af projektgruppen er korrekt i forhold til de ønskede mål.-->

## Correctness

In this report correctness will refer to the codes correctness, this will in practise mean how well code with a specific purpose
actually performs its task.
An example of this could be to ensure that when the train-method is invoked on the neural network, samples and labels will 
be converted to the correct data structure on which the the neural network relies, and that the network can train afterwards.
The underlying code on how the training is actually done, will not be tested in this case as the code for doing is from an
external library. This means that we will not test the external resources directly, but instead rely on testing the code surrounding it,
if the group test it and it behaves properly, then errors are likely to lie elsewhere. 
This also helps to keep the test decoupled from the external libraries.

<!-- Korrekthed kan betyde mange ting, derfor ønskes det at specificer præcist hvad der menes når gruppen bruger ordet korrekt. 
Der referes til at hver sektion af koden har et spæcifikt formål som er udtænkt af gruppen for at opnå en specifikt effekt. 

Derfor vil det at sikre korrektheden bero sig på at sikre at koden går de aktioner som er nødvendige for at opnå den tiltænkte effekt. 
Så et eksempel kunne være, at sikre at når gruppen kalder train-metoden på ANN-klassen at samples og labels bliver konverteret til de 
rigtige data strukturer som skal bruges i det valgte library til ANN og derfor bliver givet videre til library koden og librariet bliver 
bedt om at træne. Det er ikke relevant for gruppen at kontroller om det bagved liggende kode agerer som det skal. Da koden der udfører 
denne opgave ikke tilhører gruppen, samtidigt med at gruppen havde et ønske om at holde testen uafhængig af valg omkring hvilke 
træningsmetoder og aktiveringsmetoder benyttet. På baggrund af dette vurderede gruppen det irrelevant at teste tredje parts kode. -->

## Fuzzy input, Fuzzy output

On of the difficulties the group faced when testing, was that it was hard to verify what the correct output from many parts of the project.
Eg. in practise the function testing for the presence of red circles, then the group can test that it detects red circles, but guarantying
that it will is difficult, as the detection can be hindered by outside factors in the environment eg. lighting or reflections.   
<!-- En af de store problemstilling omkring at teste koden i dette projekt er at meget lidt af koden er forudsigeligt, forstået på 
den måde at selv om vi har en test der siger den kan genkende røde cirkler i billeder, betyder det ikke at koden faktisk altid vil 
kunne genkende røde cirkler, da der er forudsætninger vi ikke kan kontroller så som belysning. Foruden dette kan der heller ikke 
garanteres at midten at den fundende cirkle altid vil være den helt eksakte midte af cirklen på billedet. --> 

This means the tests only can guarantee the circle detection, under the circumstances tested the group tested under. But 
as the EV3 are likely to diverge from these circumstances, in real world use. This is why the test focuses on the behaves correctly,
 and integrates correctly with the different components in the system.

<!-- Det betyder at vores tests ikke kan garanter at fordi testen er successfull så vil enheden altid være successfull i den 
opgave. Testen specificer bare at givet optimale omstændigheder er enheden i stand til ogpaven, hvilket giver den sikkerhed 
at der ikke er lavet fejl i koden i forhold til integrationen imellem de forskellige komponenter. -->

## Mockito

A framework called `Mockito` used for mocking. `Mockito` describes themselves as a mocking framework, but acknowledges that 
there are some controversy on the naming as the framework technically is a `Test Spy Framework`. The framework allows for "spying"
on classes, verifying method calls, and parameter signatures.     

<!-- Gruppen benyttede et framework kaldet `Mockito` til at mocke med. `Mockito` beskriver sig selv som et Mocking Framework, 
men anerkender at der lidt uenigheder omkring ordvalget, da det rent teknisk mere er et `Test Spy Framework`. Frameworket 
tillader at spionerer på en klasse og verificer metode kald og parameter angivelser som man gender det fra mocks og stubs. --> 

Using this framework allowed for abstracting out third party code, out of the testing environment. And hence isolating the tests, 
to only deal with relevant code the test targets. 

<!-- At benytte dette framework gjorde det muligt for gruppen at abstraherer implementation kode fra tredje parts 
libraries ud af testen. Dermed tillade at isolerer testen til kun at arbejde på den klasse som er subjektet for testen. -->

## Testing the Neural Network

To test the neural network it was chosen to abstract the implementation itself away, the goal of the test were not to test OpenCV's implementation of a neural network,
but rather test if OpenCV's methods were utilized correctly by the group.   
<!-- For at teste det neurale netværk valgte gruppen at abstrahere selve implementation væk, målet med testende var ikke at teste OpenCVs implementation af det neurale netværk,
men derimod teste at gruppen benyttede OpenCvs implementation korrekt i forhold til de ønskede effekter. 

I projektet er eksisterer der en class ved navn `ANN` som er gruppens adapter til OpenCVs implementation a neural netværk. 
I testen bliver der lavet en `spy` på OpenCVs implementation og den bliver injected ind i adapter klassen. Dette tillader 
gruppen at verificerer at de nødvendige metoder på OpenCvs implementations kode bliver invokeret med de korrekte parameter. 
Dette hjælpe gruppen med at sikre sig at den kode som interagerer med implementationen af det virker. -->
In the project a class named `ANN` exists, this class acts as a wrapper around OpenCV's neural network. In the tests for
this class utilizes a `spy` on OpenCV's methods, which verifies that the desired functions are called with the desired parameter signatures.  
And this way ensuring that the groups code interacts correctly with OpenCV's code.

## Testing the Ellipse Dectector

The group faced some difficulties testing the `EllipseDetecter` class. The issue was not that the code it self was difficult
to test, but rather to make sure that right aspects were tested.

1. Ensure that red circles are detected.
2. Ensure no non-red circles are detected.
3. Ensure that only circular geometric shapes are detected.
4. Ensure that red circles can be detected in an environment with other red objects.

These 4 scenarios was tested by creating 4 images, each representing 1 of the 4 scenarios, and then verifies that the detector 
returns correctly.

The reason for the test not being more specific is due to multiple factors, one of which were when fine tuning the the `EllipseDetector`, small adjustments to its parameters
can have an effect on the ellipses are detected, and these were found with at trial and error approach to find the ideal ones for the groups camera and setup. For that reason 
the group prioritised an integration test approach, meaning that tests responsibility in not to verify the specific implementation of the ellipse detector, but rather that 
it actually detects an ellipse or not, and interacts with the rest of the system correctly.
   
<!-- At teste `EllipseDectector` klassen vidste sig at være lidt af en udfordring. Ikke grundet at koden nødvendigvis var svær at teste, men mere at sikrer sig at de 
rigtige aspekter af klassen blev testet. Slut produktet blev en sæt af 4 meget enkelte tests som består af: 

1. Sikrer den kan gendkende en rød cirkler.
2. Sikrer den ikke gendkende andre farver cirkler.
3. Sikrer den ikke gendkender andre røde geometriske former som en rød cirkle.
4. Sikrer den kan gendkende en rød cirkle bland mange andre røde objekter.

Disse 4 scenarier blvier testet ved hjælp af 4 simple billeder som hver repræsenterer et scenarie og der verificeres der efter at detektoren returnerer henholdvis `null` eller `not null`.

Årsagen til at testen ikke er mere specifikt skyldes forskellige årsager, blandt andet er der mange finjusterings parameter i klassen som kan have effekt på hvorledes den genkender 
cirklerne, dette betyder at en finjustering på nogle parameter vil påvirke selve dataen returneret fra detektoren, men detektoren ville stadig genkende ellipsen fuldt acceptabelt. 
Dermed bør testen ikke fejle i sådan et tilfælde. En anden årsag er at testens opgave var ikke at sikrer en specifikt implementation af en ellipse detektor, blot at den 
implementation rent faktisk er i stand til at detekterer en ellipse. -->

## Future work regarding testing

The test suite itself is not optimal, meaning if the project were to be used outside of study related environment the group will strongly advice to improve the test suite.

This could be done by expanding the tests for the `EllipseDetector` class, by defining some minimum requirements for the detection, test to ensure that it 
upholds these requirements. Requirements could revolve about factors like distance, lighting, angel to the camera, computations speed of the EV3, and more.

Additionally the group would recommend supplementing this by also reinforce the testing of the `CircleCropper` class, as its testing could be made more robust and thorough.

It could also be beneficial to to test some of OpenCVs methods, if the project were to be used for commercial purposes, as a reassurance as this project
relies on much functionality there from.

Finally the group would like to improving the tests in the future, as currently most of the testing revolves around testing these so called "happy paths", meaning
that most of the tests focuses on if the code acts as expected, when given the expected inputs. It could could be beneficial to improve the codes robustness by testing
the code with inexpedient inputs.
    
<!-- Selve test suiten er ikke optimal, hvis projektet bruges til andet end studie relateret, vil gruppen på det stærkest anbefale at test suiten forbedres.

Dette kan blandt andet gøres ved at lave nogle flere tests til `EllipseDectector` klassen, hvor der kunne defineres et minimums krav til algorithmen og der 
derved kunne garanteres mulighed for detektion i forhold til minimumskravende. Med minimumskrav kunne der kikkes på parameterer så som belysning, afstand 
til skiltet, vinkel fra kamera til skiltet, beregningshastighed og flere. 

Der udover vil gruppen anbefale at der skrives en mere fyldetsgørende test til `CircleCropper` klassen da den nuværende testning af denne klasse er meget mangelfuld. 

Der kan også være værdi i at teste noget af implementations koden fra OpenCV, hvis projektet skal bruges til eksempelvis kommerciel brug, vil der være afhængigheder 
fra OpenCV som bør testes for at sikrer der ikke sker utilsigtede hændelser.

Den sidste overvejelse omkring fremtidig arbejde omkring testing er at projektets test suite på nuværende tidspunkt bærer meget præg af at test såkaldte 
`Happy paths` altså teste at koden agerer som den skal når inputs er korrekte. Der er et generelt mangel i at teste hvorledes koden agerer på inkorrekt 
input, og sikring af at det ikke vil medfører uhensigtsmæssige resultater. -->



