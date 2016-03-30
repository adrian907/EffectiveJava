/**
 * Created by Adrian on 29.03.2016.
 */
public class _55_Optimize_judiciously {
}
/*

APHORISMS :

More computing sins are committed in the name of efficiency (without necessarily
achieving it) than for any other single reason—including blind stupidity.
—William A. Wulf [Wulf72]

We should forget about small efficiencies, say about 97% of the time: premature
optimization is the root of all evil.
—Donald E. Knuth [Knuth74]

We follow two rules in the matter of optimization:
Rule 1. Don’t do it.
Rule 2 (for experts only). Don’t do it yet—that is, not until you have a
perfectly clear and unoptimized solution.
—M. A. Jackson [Jackson75]


Don’t sacrifice sound architectural principles for performance. Strive to write
good programs rather than fast ones.

This does not mean that you can ignore performance concerns until your program
is complete.

Strive to avoid design decisions that limit performance.

The components of a design that are most difficult to change after the fact are those specifying
interactions between modules and with the outside world.

Consider the performance consequences of your API design decisions.
Making a public type mutable may require a lot of needless defensive copying
(Item 39). Similarly, using inheritance in a public class where composition would
have been appropriate ties the class forever to its superclass, which can place artificial
limits on the performance of the subclass (Item 16)

Consider the performance consequences of your API design decisions.
Making a public type mutable may require a lot of needless defensive copying
(Item 39). Similarly, using inheritance in a public class where composition would
have been appropriate ties the class forever to its superclass, which can place artificial
limits on the performance of the subclass (Item 16).As a final example,
using an implementation type rather than an interface in an API ties you to a specific
implementation, even though faster implementations may be written in the
future (Item 52).

It is a very bad idea to warp an API to achieve good performance.
The performance issue that caused you to warp the API may go away in a
future release of the platform or other underlying software, but the warped API
and the support headaches that come with it will be with you for life.

Profiling tools can help you decide where to focus your optimization efforts.
Such tools give you runtime information, such as roughly how much time each
method is consuming and how many times it is invoked. In addition to focusing
your tuning efforts, this can alert you to the need for algorithmic changes. If a quadratic
(or worse) algorithm lurks inside your program, no amount of tuning will
fix the problem.







To summarize, do not strive to write fast programs—strive to write good ones;
speed will follow. Do think about performance issues while you’re designing systems
and especially while you’re designing APIs, wire-level protocols, and persistent
data formats. When you’ve finished building the system, measure its
performance. If it’s fast enough, you’re done. If not, locate the source of the problems
with the aid of a profiler, and go to work optimizing the relevant parts of the
system. The first step is to examine your choice of algorithms: no amount of lowlevel
optimization can make up for a poor choice of algorithm. Repeat this process
as necessary, measuring the performance after every change, until you’re satisfied.































 */