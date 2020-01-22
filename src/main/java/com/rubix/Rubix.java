package com.rubix;

import com.rubix.artifacts.Solver;
import com.rubix.artifacts.State;
import com.rubix.input.KeyInput;
import com.rubix.rendering.window.Renderer;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.awt.event.KeyEvent.*;
import static java.awt.event.KeyEvent.VK_R;

public class Rubix implements Runnable{

    private Renderer renderer;
    private Solver solver;
    private boolean running;
    private State state;

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new Rubix());
    }

    public Rubix(){
        //Initialise Window
        state = new State();
        this.renderer = new Renderer(this);
        this.solver = new Solver();

        state.permutate("F");
        state.permutate("U");
        state.permutate("B");
        state.permutate("B");
        state.permutate("F");
        state.permutate("L");
        state.permutate("F");
        state.permutate("D");
        state.permutate("F");
        state.permutate("R");
        state.permutate("F");
        state.permutate("R");
        state.permutate("D");
        state.permutate("L");
        state.permutate("D");
        state.permutate("R");
        state.permutate("L'");
        state.permutate("B");

        /*state = solver.solveWhiteCross(state);
        state.permutate("F");
        state.permutate("R");
        state = solver.solveWhiteCross(state);*/
        start();
    }

    private void tick() {
        //Input Listeners
        String arg = "";
        KeyInput keyInput = new KeyInput();
        renderer.addKeyListener(keyInput);
        renderer.getFrame().addKeyListener(keyInput);

        if (KeyInput.isDown(VK_SHIFT)) {
            if (KeyInput.wasPressed(VK_F))
                arg = "F'";
            else if (KeyInput.wasPressed(VK_B))
                arg = "B'";
            else if (KeyInput.wasPressed(VK_U))
                arg = "U'";
            else if (KeyInput.wasPressed(VK_D))
                arg = "D'";
            else if (KeyInput.wasPressed(VK_L))
                arg = "L'";
            else if (KeyInput.wasPressed(VK_R))
                arg = "R'";
        } else if (KeyInput.isDown(VK_CONTROL)) {
            if (KeyInput.wasPressed((VK_G))){
                renderer.toggleGridVisible();
            }
        } else {
            if (KeyInput.wasPressed(VK_F))
                arg = "F";
            else if (KeyInput.wasPressed(VK_B))
                arg = "B";
            else if (KeyInput.wasPressed(VK_U))
                arg = "U";
            else if (KeyInput.wasPressed(VK_D))
                arg = "D";
            else if (KeyInput.wasPressed(VK_L))
                arg = "L";
            else if (KeyInput.wasPressed(VK_R))
                arg = "R";
            else if (KeyInput.wasReleased(VK_1)){
                state = solver.solveDaisy(state);
            }
            else if (KeyInput.wasReleased(VK_2)){
                state = solver.solveCross(state);
            }
        }
        if (!arg.equals("")){
            state.permutate(arg);
            state.scrambleAdd(arg);
        }
    }

    private void render() {
        renderer.render(state);
    }

    public void run() {
        //Game Loop
        double targetTicks = 60.0;
        double nanoSecondsPT = 100000000.0 / targetTicks;
        double unprocessed = 0.0;
        long lastTime = System.nanoTime();
        long timer = System.currentTimeMillis();
        int fps = 0;
        int tps = 0;
        boolean canRender = false;

        while (running){
            long now = System.nanoTime();
            unprocessed += (now - lastTime) / nanoSecondsPT;
            lastTime = now;

            if (unprocessed >= 1){
                tick();
                unprocessed--;
                tps++;
                canRender = true;
            } else {
                canRender = false;
            }

            KeyInput.update();

            try {
                Thread.sleep(15);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (canRender){
                render();
                fps++;
            }

            if (System.currentTimeMillis() - 1000 > timer){
                timer += 1000;
                //System.out.printf("FPS: %d | TPS: %d\n", fps, tps);
                fps = 0;
                tps = 0;
            }
        }

        System.exit(0);
    }

    private void start(){
        if (running)
            return;
        running = true;
        KeyInput.createTimer();
        new Thread(this, "RubixMain-Thread").start();
    }

    public void stop(){
        if (!running)
            return;
        running = false;
    }
}