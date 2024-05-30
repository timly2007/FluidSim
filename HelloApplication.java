package com.example.demo;

// imports
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.animation.AnimationTimer;
import javafx.scene.shape.Circle;
import java.io.IOException;
import java.util.Arrays;
import javafx.scene.control.Button;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        // radius of particles.
        float radius = 5;

        // amount of particles (a majority will show up on screen)
        int ParticleAmount = 1470;

        //the amount of points (max) possible on an object
        int drawingFrames = 2000;

        // the radius of impact of the forces between particles.
        float densityRadius = (float) (1.5 * 1080 / Math.pow(ParticleAmount * (1080f/2300f) * (1.73 / 2), 0.5));

        // dampening with wall
        float dampeningCoefficient = 0.4f;

        // gravity (unused atm)
        float gravity = 0f;

        //black bg
        StackPane root = new StackPane();
        root.setStyle("-fx-background-color: black");
        Scene scene = new Scene(root, 1440, 900);
        stage.setScene(scene);
        stage.show();

        // bg lines

        Line[] bgLine = new Line[26];

        for (int i = 0; i < 26; i++) {
            if(i < 10) {
                bgLine[i] = new Line(0, 0, 1440, 0);
                bgLine[i].setTranslateY(-400 + 90 * i);
            } else {
                bgLine[i] = new Line(0, 0, 0, 900);
                bgLine[i].setTranslateX(-670 + 90 * (i - 10));
            }
            bgLine[i].setStroke(Color.rgb(20, 20, 20));
            bgLine[i].setStrokeWidth(5);
            root.getChildren().add(bgLine[i]);
        }

        for (int i = 0; i < 9; i++) {
            Line line = new Line(0, 850, 270, 850);
            line.setStroke(Color.WHITE);
            line.setStrokeWidth(5);
            root.getChildren().add(line);
            line.setTranslateX(-540);
            line.setTranslateY(-300);
            line.setOpacity(0);
        }

        //create circle
        Circle[] circle = new Circle[ParticleAmount];

        // create "particleamount" particles.
        for (int i = 0; i < ParticleAmount; i++) {
            circle[i] = new Circle(radius, Color.WHITE);
            circle[i].setTranslateX((1770 - 2 * radius) * Math.random() - (1000 - radius)); // find out how random works
            circle[i].setTranslateY((1000 - 2 * radius) * Math.random() - (500 - radius));
            root.getChildren().add(circle[i]);
        }

        // create initial point circle
        Circle[] initialPoint = new Circle[drawingFrames];
        for (int s = 0; s < drawingFrames; s++) {
            initialPoint[s] = new Circle(15, Color.TRANSPARENT);
            root.getChildren().add(initialPoint[s]);
        }

        //create sliders

        Polygon settingsBG = new Polygon(0, 0, 360, 0, 360, 900, 0, 900);
        root.getChildren().add(settingsBG);
        settingsBG.setTranslateX(-540);
        settingsBG.setFill(Color.BLACK);
        settingsBG.setOpacity(0);

        Line line = new Line(0, 850, 270, 850);
        line.setStroke(Color.WHITE);
        line.setStrokeWidth(5);
        root.getChildren().add(line);
        line.setTranslateX(-540);
        line.setTranslateY(-300);
        line.setOpacity(0);

        Line line2 = new Line(0, 850, 270, 850);
        line2.setStroke(Color.WHITE);
        line2.setStrokeWidth(5);
        root.getChildren().add(line2);
        line2.setTranslateX(-540);
        line2.setTranslateY(-200);
        line2.setOpacity(0);

        Polygon slider1 = new Polygon(20, 20, 0, 20, 0, 0, 20, 0);
        root.getChildren().add(slider1);
        slider1.setTranslateX(-640);
        slider1.setTranslateY(-300);
        slider1.setFill(Color.WHITE);
        slider1.setStroke(Color.BLACK);
        slider1.setStrokeWidth(3);
        slider1.setOpacity(0);

        Polygon slider2 = new Polygon(20, 20, 0, 20, 0, 0, 20, 0);
        root.getChildren().add(slider2);
        slider2.setTranslateX(-640);
        slider2.setTranslateY(-200);
        slider2.setFill(Color.WHITE);
        slider2.setStroke(Color.BLACK);
        slider2.setStrokeWidth(3);
        slider2.setOpacity(0);

        // settings button & interface bg

        Button settings = new Button("⚙");
        Font font2 = Font.font("Courier New", FontWeight.BOLD, 36);
        settings.setFont(font2);
        root.getChildren().add(settings);
        settings.setTranslateX(670);
        settings.setTranslateY(-400);
        settings.setStyle("-fx-text-fill: white; -fx-background-color: black; -fx-border-width: 3px; -fx-border-color: white");
        settings.setOpacity(0.75);

        // button for deleting all objects

        Button deleteButton = new Button("Delete");
        Font font = Font.font("Georgia", FontWeight.BOLD, 24);
        deleteButton.setFont(font);
        root.getChildren().add(deleteButton);
        deleteButton.setTranslateX(-635);
        deleteButton.setTranslateY(400);
        deleteButton.setStyle("-fx-text-fill: white; -fx-border-color: white; -fx-background-color: black; -fx-border-width: 3px");
        deleteButton.setOpacity(0);

        // button for night mode lol

        Button nightButton = new Button("✸/☽");
        nightButton.setFont(font);
        root.getChildren().add(nightButton);
        nightButton.setTranslateX(-430);
        nightButton.setTranslateY(400);
        nightButton.setStyle("-fx-text-fill: white; -fx-border-color: white; -fx-background-color: black; -fx-border-width: 3px");
        nightButton.setOpacity(0);

        //  label sliders

        Button labelRadius = new Button("Radius");
        labelRadius.setFont(font);
        root.getChildren().add(labelRadius);
        labelRadius.setTranslateX(-540);
        labelRadius.setTranslateY(-350);
        labelRadius.setStyle("-fx-text-fill: white; -fx-border-color: transparent; -fx-background-color: transparent; -fx-border-width: 3px");
        labelRadius.setOpacity(0);

        Button labelSpeed = new Button("Air Speed");
        labelSpeed.setFont(font);
        root.getChildren().add(labelSpeed);
        labelSpeed.setTranslateX(-540);
        labelSpeed.setTranslateY(-250);
        labelSpeed.setStyle("-fx-text-fill: white; -fx-border-color: transparent; -fx-background-color: transparent; -fx-border-width: 3px");
        labelSpeed.setOpacity(0);

        Button particleVisibility = new Button("◉");
        particleVisibility.setFont(font);
        root.getChildren().add(particleVisibility);
        particleVisibility.setTranslateX(-525);
        particleVisibility.setTranslateY(400);
        particleVisibility.setStyle("-fx-text-fill: white; -fx-border-color: white; -fx-background-color: black; -fx-border-width: 3px");
        particleVisibility.setOpacity(0);

        Button pause = new Button("Play");
        pause.setFont(font);
        root.getChildren().add(pause);
        pause.setTranslateX(660);
        pause.setTranslateY(410);
        pause.setStyle("-fx-text-fill: white; -fx-border-color: white; -fx-background-color: black; -fx-border-width: 3px");
        pause.setOpacity(0.5);

        //animation

        AnimationTimer animationTimer = new AnimationTimer() {

            // defining all variables yay
            float[] centerX = new float[ParticleAmount];
            float[] centerY = new float[ParticleAmount];
            float[] velocityY = new float[ParticleAmount];
            float[] velocityX = new float[ParticleAmount];
            float[] distanceY = new float[ParticleAmount];
            float[] distanceX = new float[ParticleAmount];
            float[] distance = new float[ParticleAmount];
            float[] forceMagnitude = new float[ParticleAmount];
            float[] forceNetY = new float[ParticleAmount];
            float[] forceNetX = new float[ParticleAmount];
            float[] forceNetMouseY = new float[ParticleAmount];
            float[] forceNetMouseX = new float[ParticleAmount];
            float[] forceMouseY = new float[ParticleAmount];
            float[] forceMouseX = new float[ParticleAmount];
            float[] velocityDifference = new float[ParticleAmount];
            float[] velocityMapped = new float[ParticleAmount];
            int[] variableColor1 = new int[ParticleAmount];
            int[] variableColor2 = new int[ParticleAmount];
            int[] variableColor3 = new int[ParticleAmount];
            int[] mouseState = new int [100];
            float sumMouseX = 0;
            float sumMouseY = 0;

            float[] distanceMouseY = new float[drawingFrames];
            float[] distanceMouseX = new float[drawingFrames];
            float[] distanceMouse = new float[drawingFrames];
            float[] forceMagnitudeMouse = new float[drawingFrames];
            float[][] mouseX = new float[drawingFrames][drawingFrames];
            float[][] mouseY = new float[drawingFrames][drawingFrames];
            double[] pointArray = new double[0];
            int objectAmount = 0;
            Polygon[] polygon = new Polygon[100];
            boolean objectState = Boolean.TRUE;

            // air speed (try to keep relatively low, < 20).
            float airfoilSpeed = 8;
            // radius of particles, trade-off between flow and velocity.
            int animationRadius = (int) radius;

            float slider1X = 0;
            float slider2X = 0;

            float firstMousePointX = 0;
            float firstMousePointY = 0;

            Boolean settings2 = Boolean.TRUE;
            Boolean settingsState = Boolean.TRUE;
            Boolean settingsState2 = Boolean.TRUE;

            Boolean settingsState3 = Boolean.TRUE;

            Boolean nightState = Boolean.TRUE;

            float settingsOpacity = 0;

            int settingsColor = 0;

            float colorMultiplier = 1;

            public float sine(float a) {
                float dummyVariable = (float) (a - Math.pow(a, 3)/6 + Math.pow(a, 5)/120 - Math.pow(a, 7)/5040 + Math.pow(a, 9)/362880);
                return dummyVariable;
            }
            public float cosine(float b) {
                float dummyVariable2 = (float) (1 - Math.pow(b, 2)/2 + Math.pow(b, 4)/24 - Math.pow(b, 6)/720 + Math.pow(b, 8)/40320);
                return dummyVariable2;
            }

            public String grayScale(int c) {
                int dummyVariable3 = c / 16;
                int dummyVariable4 = c % 16;

                String dummyVariable5 = Integer.toString(dummyVariable3);
                String dummyVariable6 = Integer.toString(dummyVariable4);

                if (dummyVariable3 == 10) {
                    dummyVariable5 = "A";
                } else if (dummyVariable3 == 11) {
                    dummyVariable5 = "B";
                } else if (dummyVariable3 == 12) {
                    dummyVariable5 = "C";
                } else if (dummyVariable3 == 13) {
                    dummyVariable5 = "D";
                } else if (dummyVariable3 == 14) {
                    dummyVariable5 = "E";
                } else if (dummyVariable3 == 15) {
                    dummyVariable5 = "F";
                }

                if (dummyVariable4 == 10) {
                    dummyVariable6 = "A";
                } else if (dummyVariable4 == 11) {
                    dummyVariable6 = "B";
                } else if (dummyVariable4 == 12) {
                    dummyVariable6 = "C";
                } else if (dummyVariable4 == 13) {
                    dummyVariable6 = "D";
                } else if (dummyVariable4 == 14) {
                    dummyVariable6 = "E";
                } else if (dummyVariable4 == 15) {
                    dummyVariable6 = "F";
                }

                String hexValue = ("#" + dummyVariable5 + dummyVariable6 + dummyVariable5 + dummyVariable6 + dummyVariable5 + dummyVariable6);

                return hexValue;
            }

            public void handle(long now) {
                //center changes by velocity per frame.
                for (int i = 0; i < ParticleAmount; i++) {

                    // reset force (for calculations) for every frame.
                    forceNetY[i] = 0;
                    forceNetX[i] = 0;
                    forceNetMouseY[i] = 0;
                    forceNetMouseX[i] = 0;

                    // check density between each particle and every other particle.
                    for (int j = 0; j < ParticleAmount; j++) {
                        //distance between every particle (j) and chosen particle (i). if j belongs to the object, calculate center differently.

                        distanceY[j] = centerY[i] - centerY[j];
                        distanceX[j] = centerX[i] - centerX[j];

                        if (Math.abs(distanceY[j]) < densityRadius && Math.abs(distanceX[j]) < densityRadius) {

                            distance[j] = (float) Math.sqrt(distanceY[j] * distanceY[j] + distanceX[j] * distanceX[j]);

                            // calculates the angle of the force applied (to split up later), and the force itself.
                            if (distance[j] <= densityRadius) {
                                // if distance is less than the radius, repel. (decreasing high density)
                                forceMagnitude[j] = (float) (120 * (Math.pow((densityRadius * densityRadius - distance[j] * distance[j]), 3)) / (Math.pow(densityRadius, 6)));
                            } else if (3 * densityRadius > distance[j] && distance[j] > densityRadius) {
                                // if the distance is between 1 - 2, apply force that pulls (increasing lower density)
                                forceMagnitude[j] = (float) (-2 * Math.pow((distance[j] - densityRadius) / (distance[j] + 1), 2));
                            }

                            if (distance[j] != 0 && distance[j] < (3 * densityRadius)) {
                                // define net force, use absolute value + signum due to arctan only taking positive values and range of cosine being only positive.
                                // outputs the net "force" in component directions.
                                forceNetY[i] = (forceNetY[i] + (distanceY[j] / distance[j]) * forceMagnitude[j]);
                                forceNetX[i] = (forceNetX[i] + (distanceX[j] / distance[j]) * forceMagnitude[j]);
                            }
                        }
                    }

                    // force of mouse, for total points mouseState and for total objects objectAmount

                    for (int h = 0; h <= objectAmount; h++) {
                        forceMouseY[h] = 0;
                        forceMouseX[h] = 0;
                        for (int o = 1; o < mouseState[h]; o++) {
                            //distance between every particle (j) and chosen particle (i). if j belongs to the object, calculate center differently.

                            distanceMouseY[o] = centerY[i] - mouseY[h][o];
                            distanceMouseX[o] = centerX[i] - mouseX[h][o];

                            if (Math.abs(distanceMouseY[o]) < densityRadius && Math.abs(distanceMouseX[o]) < densityRadius) {
                                distanceMouse[o] = (float) Math.sqrt(distanceMouseY[o] * distanceMouseY[o] + distanceMouseX[o] * distanceMouseX[o]);

                                // calculates the angle of the force applied (to split up later), and the force itself.

                                if (distanceMouse[o] <= densityRadius && distanceMouse[o] >= 15) {
                                    // for objects.
                                    forceMagnitudeMouse[o] = (float) (30 * (Math.pow((densityRadius * densityRadius - distanceMouse[o] * distanceMouse[o]), 3)) / (Math.pow(densityRadius, 6)));
                                }

                                if (distanceMouse[o] != 0 && distanceMouse[o] < densityRadius) {
                                    // define net force, use absolute value + signum due to arctan only taking positive values and range of cosine being only positive.
                                    // outputs the net "force" in component directions.
                                    forceMouseY[h] = (forceMouseY[h] + (distanceMouseY[o] / distanceMouse[o]) * forceMagnitudeMouse[o]);
                                    forceMouseX[h] = (forceMouseX[h] + (distanceMouseX[o] / distanceMouse[o]) * forceMagnitudeMouse[o]);
                                }
                            }
                        }
                        forceNetMouseY[i] = forceNetMouseY[i] + forceMouseY[h];
                        forceNetMouseX[i] = forceNetMouseX[i] + forceMouseX[h];
                    }

                    // velocity updater
                    velocityX[i] = (float) (airfoilSpeed + (0.2 * forceNetX[i] + 0.3 * forceNetMouseX[i] + velocityX[i]) / 1.8);
                    velocityY[i] = (float) ((0.2 * forceNetY[i] + 0.3 * forceNetMouseY[i] + velocityY[i] + gravity) / 1.8);

                    // calculate new position.
                    centerY[i] = (float) (circle[i].getTranslateY() + velocityY[i]);
                    centerX[i] = (float) (circle[i].getTranslateX() + velocityX[i]);

                    // boundary checker
                    if (centerY[i] + velocityY[i] >= 540) {
                        // velocityY[i] = -dampeningCoefficient * velocityY[i] - 1; (use later if need)
                        centerY[i] = 540;
                    } else if (centerY[i] + velocityY[i] <= -540) {
                        // velocityY[i] = -dampeningCoefficient * velocityY[i] + 1;
                        centerY[i] = -540;
                    }
                    if (centerX[i] >= 1100) {
                        // velocityX[i] = -dampeningCoefficient * velocityX[i] - 1;
                        centerX[i] = (float) (-1100 - Math.random() * 100);
                        velocityX[i] = 0;
                    } else if (centerX[i] <= -1200) {
                        // velocityX[i] = -dampeningCoefficient * velocityX[i] + 1;
                        velocityX[i] = 0;
                    }

                    // position updater
                    circle[i].setTranslateY(centerY[i]);
                    circle[i].setTranslateX(centerX[i]);

                    velocityDifference[i] = (float) (-7 * airfoilSpeed + Math.sqrt(velocityX[i] * velocityX[i] + velocityY[i] * velocityY[i]));
                    if (velocityDifference[i] <= 0) {
                        velocityMapped[i] = (float) (-0.75 * Math.abs(velocityDifference[i] / (7 * airfoilSpeed)) + 0.25);
                    } else if (velocityDifference[i] > 7 * airfoilSpeed) {
                        // max velocity
                        velocityMapped[i] = 0.75f;
                    } else {
                        velocityMapped[i] = (float) (0.75 * Math.abs(velocityDifference[i] / (7 * airfoilSpeed)) + 0.25);
                    }

                    // color code (and old code)

                    //variableColor1[i] = (int) (Math.abs(127 * 6 * velocityMapped[i]) - 127 * 2);
                    //variableColor2[i] = (int) (-Math.abs(127 * 6 * (-velocityMapped[i] + 0.333)) + 127 * 4);
                    //variableColor3[i] = (int) (-Math.abs(127 * 6 * (-velocityMapped[i] - 0.333)) + 127 * 4);

                    variableColor1[i] = (int) (Math.abs(762 * velocityMapped[i]) - 254);
                    variableColor2[i] = (int) (-Math.abs(762 * -velocityMapped[i] + 254) + 504);
                    variableColor3[i] = (int) (-Math.abs(762 * -velocityMapped[i] - 254) + 504);

                    // check if out of bounds for rgb function
                    if (variableColor1[i] > 255) {
                        variableColor1[i] = 255;
                    } else if (variableColor1[i] < 0) {
                        variableColor1[i] = 0;
                    }
                    if (variableColor2[i] > 255) {
                        variableColor2[i] = 255;
                    } else if (variableColor2[i] < 0) {
                        variableColor2[i] = 0;
                    }
                    if (variableColor3[i] > 255) {
                        variableColor3[i] = 255;
                    } else if (variableColor3[i] < 0) {
                        variableColor3[i] = 0;
                    }

                    variableColor1[i] = (int) (colorMultiplier * variableColor1[i]);
                    variableColor2[i] = (int) (colorMultiplier * variableColor2[i]);
                    variableColor3[i] = (int) (colorMultiplier * variableColor3[i]);

                    // actual filling function

                    circle[i].setFill(Color.rgb(variableColor1[i], variableColor2[i], variableColor3[i]));
                    circle[i].setOpacity(0.5);

                    circle[i].setRadius(animationRadius);
                }

                // check if mouse is pressed/dragged (take location while it is dragged)
                root.setOnMouseDragged(e -> {
                    if (settingsState2) {
                        firstMousePointX = (float) (e.getX() - 720);
                        // slightly less due to weird mouse
                        firstMousePointY = (float) (e.getY() - 440);
                        settingsState2 = Boolean.FALSE;
                    }
                    if (!settingsState) {
                        firstMousePointX = (float) (e.getX() - 720);
                        if ((Math.abs(firstMousePointY - slider1.getTranslateY())) < 17) {
                            slider1.setTranslateX(firstMousePointX);
                        } else {
                            slider2.setTranslateX(firstMousePointX);
                        }
                        if (slider1.getTranslateX() > -405) {
                            slider1.setTranslateX(-405);
                        } else if (slider1.getTranslateX() < -675) {
                            slider1.setTranslateX(-675);
                        }
                        if (slider2.getTranslateX() > -405) {
                            slider2.setTranslateX(-405);
                        } else if (slider2.getTranslateX() < -675) {
                            slider2.setTranslateX(-675);
                        }
                    }
                    if (!settings2 && ((Math.abs(firstMousePointX - slider1.getTranslateX()) < 17 && (Math.abs(firstMousePointY - slider1.getTranslateY()) < 17)) || ((Math.abs(firstMousePointY - slider2.getTranslateY()) < 17) && (Math.abs(firstMousePointX - slider2.getTranslateX()) < 17)))) {
                        settingsState = Boolean.FALSE;
                    } else if (settingsState) {
                        if (objectState) {
                            objectAmount = objectAmount + 1;
                            objectState = Boolean.FALSE;
                        }
                        mouseState[objectAmount] = mouseState[objectAmount] + 1;
                        mouseX[objectAmount][mouseState[objectAmount]] = (float) (e.getX() - 720);
                        mouseY[objectAmount][mouseState[objectAmount]] = (float) (e.getY() - 450);
                        // testing System.out.println(mouseX[mouseState]);
                        // testing System.out.println(mouseY[mouseState]);

                        initialPoint[mouseState[objectAmount]].setTranslateX(mouseX[objectAmount][mouseState[objectAmount]]);
                        initialPoint[mouseState[objectAmount]].setTranslateY(mouseY[objectAmount][mouseState[objectAmount]]);
                        if (mouseState[objectAmount] == 1) {
                            // highlight initial point
                            initialPoint[1].setFill(Color.rgb(128, 128, 128));
                            initialPoint[1].setRadius(30);
                        } else {
                            if (nightState) {
                                initialPoint[mouseState[objectAmount]].setFill(Color.WHITE);
                            } else {
                                initialPoint[mouseState[objectAmount]].setFill(Color.BLACK);
                            }
                        }

                    }
                });

                // once released, draw polygon. points are taken as regular particles but are not able to be moved
                root.setOnMouseReleased(e -> {
                    settingsState = Boolean.TRUE;
                    settingsState2 = Boolean.TRUE;

                    float maxMouseX[] = new float[100];
                    float maxMouseY[] = new float[100];
                    float minMouseX[] = new float[100];
                    float minMouseY[] = new float[100];
                    float centerMouseX[] = new float[100];
                    float centerMouseY[] = new float[100];
                    if (!objectState && settingsState) {

                        sumMouseX = 0;
                        sumMouseY = 0;

                        // create new array for polygon
                        double[] pointArray2 = Arrays.copyOf(pointArray, pointArray.length + 2 * mouseState[objectAmount]);

                        for (int k = 1; k <= mouseState[objectAmount]; k++) {

                            // all points go into array
                            pointArray2[2 * k - 2] = mouseX[objectAmount][k];
                            pointArray2[2 * k - 1] = mouseY[objectAmount][k];

                            // centering
                            if(k == 1) {
                                maxMouseX[objectAmount] = mouseX[objectAmount][k];
                                maxMouseY[objectAmount] = mouseY[objectAmount][k];
                                minMouseX[objectAmount] = mouseX[objectAmount][k];
                                minMouseY[objectAmount] = mouseY[objectAmount][k];
                            }
                            maxMouseX[objectAmount] = Math.max(mouseX[objectAmount][k], maxMouseX[objectAmount]);
                            minMouseX[objectAmount] = Math.min(mouseX[objectAmount][k], minMouseX[objectAmount]);
                            maxMouseY[objectAmount] = Math.max(mouseY[objectAmount][k], maxMouseY[objectAmount]);
                            minMouseY[objectAmount] = Math.min(mouseY[objectAmount][k], minMouseY[objectAmount]);

                            // erase drawing points.
                            initialPoint[k].setFill(Color.TRANSPARENT);
                        }

                        // centering

                        centerMouseX[objectAmount] = (maxMouseX[objectAmount] + minMouseX[objectAmount]) / 2;
                        centerMouseY[objectAmount] = (maxMouseY[objectAmount] + minMouseY[objectAmount]) / 2;

                        // create polygon with points taken while pressed
                        polygon[objectAmount] = new Polygon(pointArray2);
                        root.getChildren().add(polygon[objectAmount]);
                        polygon[objectAmount].setTranslateX(centerMouseX[objectAmount]);
                        polygon[objectAmount].setTranslateY(centerMouseY[objectAmount]);
                        polygon[objectAmount].setStrokeWidth(3);

                        objectState = Boolean.TRUE;
                    }
                });

                // polygon coloring according to night mode

                if (nightState && objectState) {
                    for (int i = 1; i <= objectAmount; i++) {
                        polygon[i].setFill(Color.BLACK);
                        polygon[i].setStroke(Color.WHITE);
                    }
                } else if (objectState) {
                    for (int i = 1; i <= objectAmount; i++) {
                        polygon[i].setFill(Color.WHITE);
                        polygon[i].setStroke(Color.BLACK);
                    }
                }

                // button actions


                settings.setOnAction(actionEvent ->  {
                    if(settings2) {

                        deleteButton.setOnAction((ActionEvent actionEvent2) -> {
                            for (int h = 1; h <= objectAmount; h++) {
                                for (int o = 1; o < mouseState[h]; o++) {
                                    mouseX[h][o] = 10000;
                                    mouseY[h][o] = 10000;
                                }
                                mouseState[h] = 0;
                                polygon[h].setFill(Color.TRANSPARENT);
                                polygon[h].setStroke(Color.TRANSPARENT);
                            }
                            objectAmount = 0;
                        });

                        nightButton.setOnAction((ActionEvent actionEvent2) -> {
                            if (nightState) {
                                root.setStyle("-fx-background-color: white");

                                for (int u = 0; u < 26; u++) {
                                    bgLine[u].setStroke(Color.rgb(235, 235, 235));
                                }

                                settings.setStyle("-fx-border-color: white;");

                                colorMultiplier = 0.5f;

                                nightState = Boolean.FALSE;
                            } else {
                                root.setStyle("-fx-background-color: black");

                                for (int u = 0; u < 26; u++) {
                                    bgLine[u].setStroke(Color.rgb(20, 20, 20));
                                }

                                colorMultiplier = 1;

                                nightState = Boolean.TRUE;

                                settings.setStyle("-fx-border-color: black;");
                            }
                        });

                        settings2 = Boolean.FALSE;
                        settingsState3 = Boolean.FALSE;
                    } else {

                        deleteButton.setOnAction((ActionEvent actionEvent2) -> {
                        });

                        nightButton.setOnAction((ActionEvent actionEvent2) -> {
                        });

                        settings2 = Boolean.TRUE;
                        settingsState3 = Boolean.TRUE;
                    }
                });

                if (settingsState3) {

                    if (settingsOpacity >= 0) {
                        settingsOpacity = settingsOpacity - 0.07f;
                    }

                    if (settingsColor < 255) {
                        settingsColor = settingsColor + 36;
                        if (settingsColor > 255) {
                            settingsColor = 255;
                        }
                    }

                    deleteButton.setOpacity(settingsOpacity);
                    line.setOpacity(settingsOpacity);
                    line2.setOpacity(settingsOpacity);
                    slider1.setOpacity(2 * settingsOpacity);
                    slider2.setOpacity(2 * settingsOpacity);
                    settingsBG.setOpacity(settingsOpacity);
                    labelRadius.setOpacity(settingsOpacity);
                    labelSpeed.setOpacity(settingsOpacity);
                    nightButton.setOpacity(settingsOpacity);
                    particleVisibility.setOpacity(settingsOpacity);

                    settings.setStyle("-fx-text-fill:" + grayScale(settingsColor) + "; -fx-background-color:" + grayScale(255 -settingsColor) + "; -fx-border-width: 3px");


                } else {

                    if (settingsOpacity <= 0.5) {
                        settingsOpacity = settingsOpacity + 0.07f;
                    }

                    if (settingsColor > 0) {
                        settingsColor = settingsColor - 36;
                        if (settingsColor < 0) {
                            settingsColor = 0;
                        }
                    }

                    deleteButton.setOpacity(settingsOpacity);
                    line.setOpacity(settingsOpacity);
                    line2.setOpacity(settingsOpacity);
                    slider1.setOpacity(2 * settingsOpacity);
                    slider2.setOpacity(2 * settingsOpacity);
                    settingsBG.setOpacity(settingsOpacity);
                    labelRadius.setOpacity(settingsOpacity);
                    labelSpeed.setOpacity(settingsOpacity);
                    nightButton.setOpacity(settingsOpacity);
                    particleVisibility.setOpacity(settingsOpacity);

                    settings.setStyle("-fx-text-fill:" + grayScale(settingsColor) + "; -fx-background-color:" + grayScale(255 - settingsColor) + "; -fx-border-color:" + grayScale(settingsColor) + "; -fx-border-width: 3px");

                }

                // slider settings

                slider1X = (float) (Math.abs(slider1.getTranslateX() + 675)/270);
                slider2X = (float) (Math.abs(slider2.getTranslateX() + 675)/270);

                animationRadius = (int) (slider1X * 30) + 2;
                airfoilSpeed = (float) (slider2X * 4.7 + 0.1);
            }
        };
        int[] pauseVariable = {1};

        pause.setOnAction((ActionEvent actionEvent3) -> {

            if(pauseVariable[0] == 1) {
                animationTimer.start();
                pauseVariable[0] = pauseVariable[0] * -1;
                pause.setText("Pause");
            } else {
                animationTimer.stop();
                pauseVariable[0] = pauseVariable[0] * -1;
                pause.setText("Play");
            }
        });
    }

    public static void main(String[] args) {
        launch();
    }
}