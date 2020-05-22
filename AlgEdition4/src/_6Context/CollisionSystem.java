package _6Context;

/******************************************************************************
 *  Compilation:  javac CollisionSystem.java
 *  Execution:    java CollisionSystem n               (n random particles)
 *                java CollisionSystem < input.txt     (from a file) 
 *  Dependencies: StdDraw.java Particle.java MinPQ.java
 *  Data files:   https://algs4.cs.princeton.edu/61event/diffusion.txt
 *                https://algs4.cs.princeton.edu/61event/diffusion2.txt
 *                https://algs4.cs.princeton.edu/61event/diffusion3.txt
 *                https://algs4.cs.princeton.edu/61event/brownian.txt
 *                https://algs4.cs.princeton.edu/61event/brownian2.txt
 *                https://algs4.cs.princeton.edu/61event/billiards5.txt
 *                https://algs4.cs.princeton.edu/61event/pendulum.txt
 *
 *  Creates n random particles and simulates their motion according
 *  to the laws of elastic collisions.
 *
 ******************************************************************************/

import java.awt.Color;
import java.io.File;

import _2Sort.MinPQ;
import base.stdlib.In;
import base.stdlib.Particle;
import base.stdlib.StdDraw;
import base.stdlib.StdOut;

/**
 * The {@code CollisionSystem} class represents a collection of particles
 * moving in the unit box, according to the laws of elastic collision.
 * This event-based simulation relies on a priority queue.
 * <p>
 * For additional documentation,
 * see <a href="https://algs4.cs.princeton.edu/61event">Section 6.1</a> of
 * <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 *
 * 我们对于事件驱动模拟的主要兴趣在于避免时间驱动模拟的内循环所必须的大量计算。
 * - 时间驱动模拟 O(N^2/tick)
 * - 事件驱动模拟 优先队列
 *
 * 对N个能够互相碰撞的
 * @author Robert Sedgewick
 * @author Kevin Wayne
 * @author AMY
 */
public class CollisionSystem {
    // number of redraw events per clock tick
    private static final double HZ = 0.5;
    // FPS Frame per Second
    private static final double FPS = 45;
    // the amount of time  这里书写的有问题，原话：
    // 为了处理另一种典型情况，也就是预测到的碰撞距现在的时间太远时，就需要一个limit参数指定有效时间段，
    // 这样就可以忽略时间晚于limit发生的所有事件了。
    private double limit;
    // the priority queue
    private MinPQ<Event> pq;
    // simulation clock time
    private double t = 0.0;
    private double old_t = 0.0;
    // particles
    private Particle[] particles;
    // particles back
    private Particle[] particlesBak;

    /**
     * Initializes a system with the specified collection of particles.
     * The individual particles will be mutated during the simulation.
     *
     * @param particles the array of particles
     */
    public CollisionSystem(Particle[] particles) {
        this.particlesBak = particles.clone();
    }

    // updates priority queue with all new events for particle a
    private void predict(Particle a) {
        if (a == null) {
            return;
        }
        // particle-particle collisions
        for (Particle p : particles) {
            if (a == p) {
                // Particle中已经做了这方面的处理 , 多写一次循环就多了一次判断.
                continue;
            }
            double dt = a.timeToHit(p);
            if (t + dt <= limit) {
                pq.insert(new Event(t + dt, a, p));
            }
        }

        // particle-wall collisions
        double dtX = a.timeToHitVerticalWall();
        double dtY = a.timeToHitHorizontalWall();
        if (t + dtX <= limit) {
            pq.insert(new Event(t + dtX, a, null));
        }
        if (t + dtY <= limit) {
            pq.insert(new Event(t + dtY, null, a));
        }
    }

    // redraw all particles
    private void redraw() {
        StdDraw.clear();
        for (Particle p : this.particles) {
            p.draw();
        }
        StdDraw.text(0.08,0.95,String.format("t: %.3f ",(t-old_t) ));
        StdDraw.show();
        StdDraw.pause((int) (1000 / FPS));
        if (t < limit) {
            pq.insert(new Event(t + 1.0 / HZ, null, null));
        }
    }

    private void init(double limit) {
        // time clock tick
        this.t = 0.0;
        // the amount of time
        this.limit = limit;
        // initialize PQ with collision events and redraw event
        this.pq = new MinPQ<>();
        // get particles
        this.particles = this.particlesBak.clone();
        // all events
        for (Particle p : particles) {
            predict(p);
        }
        // redraw event
        pq.insert(new Event(t, null, null));
    }

    /**
     * Simulates the system of particles for the specified amount of time.
     *
     * @param limit the amount of time
     */
    public void simulate(double limit) {
        init(limit);

        // the main event-driven simulation loop
        while (!pq.isEmpty()) {

            // get impending event, discard if invalidated
            Event e = pq.delMin();
            if (!e.isValid()) {
                continue;
            }
            Particle a = e.a;
            Particle b = e.b;

            // physical collision, so update positions, and then simulation clock
            for (Particle p : particles) {
                // 这里理论上是会掉帧的,因为只有重绘事件会对整个画面进行刷新，这时候可能会出现空气碰撞的情况。
                // 但是由于差距十分微小并且如果每秒刷新频率够高，人类是感觉不到的。
                p.move(e.time - t);
            }
            old_t = t;
            t = e.time;

            // process event
            if (a != null && b != null) {
                // particle-particle collision
                a.bounceOff(b);
            } else if (a != null && b == null) {
                // particle-wall collision
                a.bounceOffVerticalWall();
            } else if (a == null && b != null) {
                // particle-wall collision
                b.bounceOffHorizontalWall();
            } else if (a == null && b == null) {
                // redraw event
                // 所有的重绘事件都在这里。
                redraw();
            }

            // update the priority queue with new collisions involving a or b
            predict(a);
            predict(b);
        }
    }


    /***************************************************************************
     *  An event during a particle collision simulation. Each event contains
     *  the time at which it will occur (assuming no supervening actions)
     *  and the particles a and b involved.
     *
     *    -  a and b both null:      redraw event
     *    -  a null, b not null:     collision with vertical wall
     *    -  a not null, b null:     collision with horizontal wall
     *    -  a and b both not null:  binary collision between a and b
     *
     ***************************************************************************/
    private class Event implements Comparable<Event> {
        // time that event is scheduled to occur
        private final double time;
        // particles involved in event, possibly null
        private final Particle a, b;
        // collision counts at event creation
        private final int countA, countB;

        // create a new event to occur at time t involving a and b
        // Event是整个算法中最精华的部分
        public Event(double t, Particle a, Particle b) {
            this.time = t;
            this.a = a;
            this.b = b;
            // 排除无效事件用.
            countA = a != null ? a.count() : -1;
            countB = b != null ? b.count() : -1;
        }

        // compare times when two events will occur
        public int compareTo(Event that) {
            return Double.compare(this.time, that.time);
        }

        // has any collision occurred between when event was created and now?
        public boolean isValid() {
            if ((a != null && a.count() != countA) || (b != null && b.count() != countB)) {
                return false;
            }
            return true;
        }
    }


    /**
     * Unit tests the {@code CollisionSystem} data type.
     * Reads in the particle collision system from a standard input
     * (or generates {@code N} random particles if a command-line integer
     * is specified); simulates the system.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        StdDraw.setCanvasSize(600, 600);
        // enable double buffering
        StdDraw.enableDoubleBuffering();

        String path1 = "/Users/helloword/Anmingyu/AlgorithmsUnion/AlgEdition4/resources/6Context/EventSimulation/billiards5.txt";
        String path2 = "/Users/helloword/Anmingyu/AlgorithmsUnion/AlgEdition4/resources/6Context/EventSimulation/brownian.txt";
        String path3 = "/Users/helloword/Anmingyu/AlgorithmsUnion/AlgEdition4/resources/6Context/EventSimulation/brownian2.txt";
        String path4 = "/Users/helloword/Anmingyu/AlgorithmsUnion/AlgEdition4/resources/6Context/EventSimulation/diffusion.txt";
        String path5 = "/Users/helloword/Anmingyu/AlgorithmsUnion/AlgEdition4/resources/6Context/EventSimulation/diffusion2.txt";
        String path6 = "/Users/helloword/Anmingyu/AlgorithmsUnion/AlgEdition4/resources/6Context/EventSimulation/diffusion3.txt";
        String path7 = "/Users/helloword/Anmingyu/AlgorithmsUnion/AlgEdition4/resources/6Context/EventSimulation/pendulum.txt";

        // create collision system and simulate
        CollisionSystem system = new CollisionSystem(getParticles(20));
        system.simulate(10000);
    }

    private static Particle[] getParticles(String path) {
        // the array of particles
        Particle[] particles;

        In in = new In(new File(path));

        int n = in.readInt();
        StdOut.println("Particle count : " + n);
        particles = new Particle[n];
        for (int i = 0; i < n; i++) {
            double rx = in.readDouble();
            double ry = in.readDouble();
            double vx = in.readDouble();
            double vy = in.readDouble();
            double radius = in.readDouble();
            double mass = in.readDouble();
            int r = in.readInt();
            int g = in.readInt();
            int b = in.readInt();
            Color color = new Color(r, g, b);
            particles[i] = new Particle(rx, ry, vx, vy, radius, mass, color);
            StdOut.println(particles[i]);
        }
        return particles;
    }

    private static Particle[] getParticles(int num) {
        Particle[] particles;
        particles = new Particle[num];
        for (int i = 0; i < particles.length; ++i) {
            particles[i] = new Particle();
        }
        return particles;
    }

}
