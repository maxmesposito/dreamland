# Dreamland
## Questions
- Database table design given the APIs above
> SQL scripts of the DB are in src/resources/ddl_scripts.sql
<img src="./dreamland_er.png">

- Database needs to have a solid double-entry ledger to track the tokens and USD (might make sense to read up about ledgers - some great sources here and here). Let's design ledgers for both the tokens and USD
> I see you have put some references into the gist, I took a first read, but I need to learn more of the functional / financial aspect.

- Data types we can use for the ledger amounts (it needs to support the smallest and largest unit of a crypto token)
> I have used SQL DECIMAL (22,12) which means 10 digits before the comma and 12 digits after the comma. It can be extended up to 35 digits if needed

- Edge cases - list some edge cases both in APIs and database that you will handle
> I have thought at the scenario if a User has less than 5 tokens, for example 4.80 and win 0.5 it should be trimmed to 0.2 to get 5 tokens in total

- Any other APIs and tools you can think of (no need to implement)
>- Error handling: there are just thrown 400 errors for bad requests, 404 for endpoint not mapped or 500 in case of server error (generically)
>- Security: all the APIS are not secured, I'd suggest to setup an authentication mechanism
>- Logging and alerting: not implemented at all
>- There's no possibility to add dynamically a user (must be done manually into DB)

- Infrastructure - This is a global system with customers across the world. Let's discuss more about setting up the infra, how to share data across different regions, how to solve for region-specific data for issues like GDPR, how to replicate some tables out of a region to a central cluster for analytics, etc. This is just a textual answer with maybe some design diagrams
> The GDPR topic is really wide, the simplest solution I can think is having separate instances of the applications 1 for EU and 1 outside EU, don't know if this is doable in the "Metaverse" scope.
> Before ingesting the "central" analytics you can scrub personal information from dumped datasets (there are several tools) before sending