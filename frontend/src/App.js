import logo from './logo.svg';
import './App.css';

import { useState } from "react";
import AccountSummary from "./AccountSummary";

export default function App() {
  const [accountId, setAccountId] = useState("ACC001");

  return (
      <div style={{ padding: 20 }}>
        <input
            value={accountId}
            onChange={(e) => setAccountId(e.target.value)}
            placeholder="Enter Account ID"
        />
        <AccountSummary accountId={accountId} />
      </div>
  );
}
