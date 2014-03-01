auto mutex      = new Mutex;
int  numThreads = 10;
int  numTries   = 1000;
int  lockCount  = 0;

void testFn()
{
    for( int i = 0; i < numTries; ++i )
    {
        synchronized( mutex )
        {
            ++lockCount;
        }
    }
}

auto group = new ThreadGroup;

for( int i = 0; i < numThreads; ++i )
    group.create( &testFn );

group.joinAll();
assert( lockCount == numThreads * numTries );
