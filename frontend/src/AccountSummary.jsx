import { useState, useEffect } from "react";

export default function AccountSummary({ accountId }) {
    const [summary, setSummary] = useState(null);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    useEffect(() => {
        if (!accountId) return;
        setLoading(true);
        setError(null);

        fetch(`/api/accounts/${accountId}/summary`)
            .then((res) => {
                if (!res.ok) throw new Error(`Error ${res.status}: Account not found`);
                return res.json();
            })
            .then((data) => setSummary(data))
            .catch((err) => setError(err.message))
            .finally(() => setLoading(false));
    }, [accountId]);

    if (loading) return <p>Loading summary...</p>;
    if (error)   return <p style={{ color: "red" }}>{error}</p>;

    return (
        <div className="summary-panel">
            <h2>Account: {summary.accountId}</h2>
            <p>Total Transactions: <strong>{summary.totalCount}</strong></p>
            <p>Total Credits: <strong>${(summary.totalCredits ?? 0).toFixed(2)}</strong></p>
            <p>Total Debits:  <strong>${(summary.totalDebits ?? 0).toFixed(2)}</strong></p>
        </div>
    );
}